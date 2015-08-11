/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Anton Huck
 *******************************************************************************/
package org.splevo.ui.sourceconnection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.splevo.project.SPLevoProject;
import org.splevo.vpm.VPMUtil;
import org.splevo.vpm.software.SoftwareElement;
import org.splevo.vpm.software.SourceLocation;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;
import org.splevo.vpm.variability.VariationPointModel;

import com.google.common.base.Strings;
import com.google.common.collect.HashMultimap;


/**
 * @author Anton
 * 
 * Connector class for the diff files. Should be replaced as the diff management changes
 *
 */
public class DiffConnector {
    
	private static Logger logger = Logger.getLogger(DiffConnector.class);

	
	private final HashMultimap<File, VariationPoint> fileVPIndex = HashMultimap.create();

	private String[] leadingFileContentsAsList;
	private String leadingFileContents;
	private String integrationFileContents;
	private String leadingFileLocation;
	private String integrationFileLocation;
	
	private final SPLevoProject splevoProject;
    
	
	/**
	 * Default Constructor
	 * @param project The SplevoProject used
	 */
	public DiffConnector(SPLevoProject project) {
		this.splevoProject = project;
		indexVariationPointLocations(getVP());
	}

	private Set<VariationPoint> getVPsInFile(File file) {
		return fileVPIndex.get(file);
	}
	
	/**
     * Populates the CU locations map with all variation points and the location names of their
     * corresponding CUs.
     *
     * @param vpContent
     *            the VPcontent to be used as population source
     */
    private void indexVariationPointLocations(VariationPointModel vpm) {
    	fileVPIndex.clear();
    	
        EList<VariationPointGroup> vpGroups = vpm.getVariationPointGroups();
        for (VariationPointGroup vpGroup : vpGroups) {
            EList<VariationPoint> vps = vpGroup.getVariationPoints();
            for (VariationPoint vp : vps) {
                // used the resource uri and not the
                // element wrappers location to save further navigation
                // and resolving overhead.
                EObject locationElement = vp.getLocation().getWrappedElement();
                String filePath = locationElement.eResource().getURI().toFileString();
                File sourceFile = new File(filePath);
                fileVPIndex.put(sourceFile, vp);
            }
        }
    }
	
	private VariationPointModel getVP() {
		File vpmModelFile = new File(splevoProject.getVpmModelPaths().get(splevoProject.getVpmModelPaths().size() - 1));
		
		try {
			return VPMUtil.loadVariationPointModel(vpmModelFile, new ResourceSetImpl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 * @param vs Variants to generateDiffFor and highlight them
	 * @return styled HTML Code 
	 */
	public String generateDiffFromVPs(Set<Variant> vs) {
		String result = "";
		HashMultimap<File, Variant> fileVPSIndex = HashMultimap.create();
		for (Variant v : vs) {
			EObject locationElement = v.getVariationPoint().getLocation().getWrappedElement();
	        String filePath = locationElement.eResource().getURI().toFileString();
	        File sourceFile = new File(filePath);
	        fileVPSIndex.put(sourceFile, v);
		}
		
		for (File f : fileVPSIndex.keySet()) {
			Set<VariationPoint> vpsInFile = getVPsInFile(f);
			populateFiles(vpsInFile);
			result += "<hr>"
					+ "<span class=\"lln\"> +   " + "</span><b>" + leadingFileLocation + "</b> (leading)\n";
			result += "<span class=\"iln\">    +" + "</span><b>" + integrationFileLocation + "</b> (integration)\n";
			result += "<hr>";
			result += generateCodeMerge(vpsInFile, fileVPSIndex.get(f));
			result += "<hr>";
		}
		return wrapInHTML(result);
	}
		
	
	private String generateCodeMerge(Set<VariationPoint> vps, Set<Variant> variationPointOI) {

		int leadingCounter = 1;
		int integrationCounter = 1;
		List<Integer> leadingPOIs = new ArrayList<Integer>();
		List<Integer> integrationPOIs = new ArrayList<Integer>();
		
		for (Variant v : variationPointOI) {
				if (v.getLeading()) {
					leadingPOIs.add(v.getImplementingElements().get(0).getSourceLocation().getStartLine());
				} else {
					integrationPOIs.add(v.getImplementingElements().get(0).getSourceLocation().getStartLine());
				}
		}
		
		List<String> result = new ArrayList<String>();
		
		while (leadingCounter <= leadingFileContentsAsList.length) {
			VariationPoint vp = getVPforLine(vps, leadingCounter, integrationCounter);
			if (vp != null) {
				// VP found
				for (Variant v : vp.getVariants()) {
					for (SoftwareElement sl : v.getImplementingElements()) {
						if (v.getLeading()) {
							leadingCounter = sl.getSourceLocation().getStartLine();
							String toAdd = appendCodeForSourceLocation(leadingFileContents, sl.getSourceLocation());
							for (String curAdd : toAdd.split("\n")) {
								if (leadingPOIs.contains(leadingCounter)) {
                                    // dark
									result.add(generateLeadingLine(leadingCounter, curAdd));
								} else {
								    // light
									result.add(generateLeadingLineBg(leadingCounter, curAdd));
								}
								leadingCounter++;
							}
						} else {
							integrationCounter = sl.getSourceLocation().getStartLine();
							String toAdd = appendCodeForSourceLocation(integrationFileContents, sl.getSourceLocation());
							for (String curAdd : toAdd.split("\n")) {
								if (integrationPOIs.contains(integrationCounter)) {
                                    // dark
									result.add(generateIntegrationLine(integrationCounter, curAdd));
								} else {
                                    // light
									result.add(generateIntegrationLineBg(integrationCounter, curAdd));
								}
								integrationCounter++;
							}
						}
					}
				}
			} else {
				// Print shared line
				result.add(getSharedLine(leadingCounter, integrationCounter, leadingFileContentsAsList));
				leadingCounter++;
				integrationCounter++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (String r : result) {
			sb.append(r + "\n");
		}
		
		return sb.toString();
	}
	
	private String getSharedLine(int leadingCounter, int integrationCounter,
			String[] leadingLines) {
		return "<span class=\"ln\">" + String.format("%02d", leadingCounter) + " " + String.format("%02d", integrationCounter) 
				+ "</span> " + leadingLines[leadingCounter - 1];
	}
	private String generateIntegrationLine(int integrationCounter, String code) {
		return "<span class=\"iln\">  " + " " + String.format("%02d", integrationCounter) + "</span> " 
			+ "<span class=\"integration-variant\">" + code + "</span>";
	}
	private String generateLeadingLine(int leadingCounter, String code) {
		return "<span class=\"lln\">" + String.format("%02d", leadingCounter) + " " + "  " + "</span> " 
				+ "<span class=\"leading-variant\">" + code 
				+ "</span>";
	}
	
	private String generateIntegrationLineBg(int integrationCounter, String code) {
		return "<span class=\"iln-bg\">  " + " " + String.format("%02d", integrationCounter) + "</span> " 
			+ "<span class=\"integration-variant-bg\">" + code + "</span>";
	}
	private String generateLeadingLineBg(int leadingCounter, String code) {
		return "<span class=\"lln-bg\">" + String.format("%02d", leadingCounter) + " " + "  " + "</span> " 
				+ "<span class=\"leading-variant-bg\">" + code + "</span>";
	}
	
	private String wrapInHTML(String body) {
		return "<html><head><style type=\"text/css\">div.code{display:block}.integration-variant-bg,.iln-bg{background-color:#Fdd"
				+ "}.integration-variant,.iln{background-color:#ffa2a2}.leading-variant,.lln{background-color:#62b5d0}.leading-va"
				+ "riant-bg,.lln-bg{background-color:#d7ecf3}span.lln,span.iln,span.lln-bg,span.iln-bg,span.ln{color:#999;padding"
				+ "-right:2px;border-right:1px solid #999;margin-right:10px}span.iln,span.iln-bg{color:red}span.lln,span.lln-bg{c"
				+ "olor:blue}span.key{color:#7f0055;font-weight:bold}span.comment{color:#3f5fbf}</style></head><body><pre>"
				+ body + "</pre></body></html>";

	}
	
	private VariationPoint getVPforLine(Set<VariationPoint> vps, int lc, int ic) {
		
		VariationPoint toRet = null;
		//first look for leading Variants
		for (VariationPoint vp : vps) {
			for (Variant v : vp.getVariants()) {
				if (v.getLeading()) {
					for (SoftwareElement sl : v.getImplementingElements()) {
						if (sl.getSourceLocation().getStartLine() == lc) {
							toRet = vp;
						}
					}
				} 
			}
		}
		
		VariationPoint onlyInt = getVPonlyInIntegration(vps, ic);
		if (onlyInt != null) {
			vps.remove(onlyInt);
			return onlyInt;
		}
		vps.remove(toRet);
		return toRet;
	}

	private VariationPoint getVPonlyInIntegration(Set<VariationPoint> vps, int ic) {
		boolean vpContainsLeading = false;
		VariationPoint vpToReturn = null;
		//then for integration
		for (VariationPoint vp : vps) {
			for (Variant v : vp.getVariants()) {
				if (!v.getLeading()) {
					for (SoftwareElement sl : v.getImplementingElements()) {
						if (sl.getSourceLocation().getStartLine() == ic) {
							vpToReturn = vp;
						}
					}
				} else {
					vpContainsLeading = true;
				}
			}
		}
		
		if (!vpContainsLeading) {
			return vpToReturn;
		} else {
			return null;
		}
	}
	
	private void populateFiles(Set<VariationPoint> vps) {
		leadingFileContents =  "";
		integrationFileContents = "";
		leadingFileLocation = "";
		integrationFileLocation = "";
		for (VariationPoint vp : vps) {
			if (Strings.isNullOrEmpty(integrationFileContents) || Strings.isNullOrEmpty(leadingFileContents)) {
				for (Variant v : vp.getVariants()) {
					if (v.getLeading() && Strings.isNullOrEmpty(leadingFileContents)) {
						IPath path = Path.fromOSString(v.getImplementingElements().get(0)
								.getSourceLocation().getFilePath());
						leadingFileLocation = path.toOSString();
						leadingFileContents = readFileFromLocation(path);
						leadingFileContentsAsList = leadingFileContents.split("\n");
					} else if (!v.getLeading() && Strings.isNullOrEmpty(integrationFileContents)) {
						IPath path = Path.fromOSString(v.getImplementingElements().get(0)
								.getSourceLocation().getFilePath());
						integrationFileLocation = path.toOSString();
						integrationFileContents = readFileFromLocation(path);
					} 
				}
			}
		}
	}
		
	/**
     * Extracts the code from the file by a given {@link SourceLocation}.
     *
     * @param sourceLocation
     *            The {@link SourceLocation}.
     */
    private static String appendCodeForSourceLocation(String code, SourceLocation sourceLocation) {
    	int startPos = code.lastIndexOf("\n", sourceLocation.getStartPosition()) + 1;
    	int endPos = code.indexOf("\n", sourceLocation.getEndPosition()) - 1;
    	return code.substring(startPos, endPos);
    }
    
    // lol, code! :D
	private static String readFileFromLocation(IPath location) {
		String code = null;
		try {
            code = FileUtils.readFileToString(location.toFile());
        } catch (IOException e) {
            logger.error("Error while reading source file", e);
            return null;
        }
		return code;
	}
}


