package org.splevo.vpm.analyzer.semantic;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.emf.impl.ASTNodeImpl;
import org.eclipse.gmt.modisco.java.emf.impl.VariableDeclarationImpl;
import org.splevo.vpm.realization.RealizationTechnique;
import org.splevo.vpm.variability.Variant;
import org.splevo.vpm.variability.VariationPoint;
import org.splevo.vpm.variability.VariationPointGroup;

public class TestVariationPointImpl implements VariationPoint {
	
	private String[] contents;

	public TestVariationPointImpl(String content){
		contents = content.split(" ");
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EClass eClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EObject eContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EReference eContainmentFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<EObject> eContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<EObject> eCrossReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eGet(EStructuralFeature arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eGet(EStructuralFeature arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eInvoke(EOperation arg0, EList<?> arg1)
			throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eIsProxy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eIsSet(EStructuralFeature arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Resource eResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eSet(EStructuralFeature arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eUnset(EStructuralFeature arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EList<Adapter> eAdapters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eDeliver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eNotify(Notification arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eSetDeliver(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EList<Variant> getVariants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealizationTechnique getRealizationTechnique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRealizationTechnique(RealizationTechnique value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ASTNode getEnclosingSoftwareEntity() {
		return new ASTNodeImpl() {
			@Override
			public EList<EObject> eContents() {
				EList<EObject> result = new BasicEList<EObject>();
				for (String content : contents) {
					result.add(getVarDec(content));
				}
				return result;
			}

			private EObject getVarDec(final String content) {
				return new VariableDeclarationImpl() {
					@Override
					public String getName() {
						return content;
					}
				};
			}
		};
	}

	@Override
	public void setEnclosingSoftwareEntity(ASTNode value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VariationPointGroup getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroup(VariationPointGroup value) {
		// TODO Auto-generated method stub
		
	}

}
