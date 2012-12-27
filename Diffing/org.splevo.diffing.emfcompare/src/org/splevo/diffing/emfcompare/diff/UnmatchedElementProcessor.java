package org.splevo.diffing.emfcompare.diff;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;

/**
 * A processor to handle unmatched elements according to their type of element.
 * 
 * The processor uses an internal switch based on the generated emf switch for the modisco java model.
 */
public class UnmatchedElementProcessor {
	
	/**
	 * Process an element to check for a model specific diff element.
	 * 
	 * @param element The element to process.
	 * @return The DiffElement or null if no specific diff type exists.
	 */
	public DiffElement process(UnmatchElement unmatchElement){
		ProcessorSwitch processorSwitch = new ProcessorSwitch(unmatchElement);
		return processorSwitch.doSwitch(unmatchElement.getElement());
	}
	
	/**
	 * Internal processor switch to handle the unmatched element according to the type
	 * of element the unmatch element is about.
	 */
	private class ProcessorSwitch extends JavaSwitch<DiffElement>{

		/** The unmatch element currently processed. */
		private UnmatchElement unmatchElement = null;
		
		/**
		 * Constructor requiring the UnmatchElement according to which the switchg should be done.
		 * @param unmatchElement
		 */
		public ProcessorSwitch(UnmatchElement unmatchElement){
			this.unmatchElement = unmatchElement;
		}

		/**
		 * Process an unmatched element which is about an import declaration.
		 * 
		 * Builds one of the alternatives:
		 * - For a right match: it builds an ImportInsert
		 * - For a left match: it builds an ImportDelete
		 * 
		 * @param element The ImportDeclaration to handle the switch case for.
		 * @return The DiffElement derived from the import declaration. 
		 */
		@Override
		public DiffElement caseImportDeclaration(ImportDeclaration element) {
			
			if (unmatchElement.getSide() == Side.LEFT) {
				// add ImportInsert
				final ImportInsert importInsert = Java2KDMDiffFactory.eINSTANCE.createImportInsert();
				importInsert.setImportLeft((ImportDeclaration) element);				
				return importInsert;
			} else {
				// add ImportDelete
				final ImportDelete importDelete = Java2KDMDiffFactory.eINSTANCE.createImportDelete();
				importDelete.setImportRight((ImportDeclaration) element);
				return importDelete;
			}
		}
		
	}
}
