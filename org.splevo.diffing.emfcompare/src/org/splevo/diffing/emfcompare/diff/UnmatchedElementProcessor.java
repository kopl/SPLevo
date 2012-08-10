package org.splevo.diffing.emfcompare.diff;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.match.metamodel.Side;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportDelete;
import org.splevo.diffing.emfcompare.java2kdmdiff.ImportInsert;
import org.splevo.diffing.emfcompare.java2kdmdiff.Java2KDMDiffFactory;

/**
 * A processor to handle unmatched elements according to their type of element.
 * 
 * The unmatch element should be set in the constructor.
 * The processor itself is called by the doSwitch() method supplying the element itself
 * which is unmatched, e.g. by <code>processor.doSwitch(unmatchElement.getElement())</code>
 * 
 * 
 * TODO Consider to extract an interface and hide the switch nature or change the switch itself to internal class
 */
public class UnmatchedElementProcessor extends JavaSwitch<DiffElement> {

	/** The unmatch element currently processed. */
	private UnmatchElement unmatchElement = null;
	
	/**
	 * Constructor requiring the UnmatchElement according to which the switchg should be done.
	 * @param unmatchElement
	 */
	public UnmatchedElementProcessor(UnmatchElement unmatchElement){
		this.unmatchElement = unmatchElement;
	}
	
	/**
	 * Process an element to check for a model specific diff element.
	 * 
	 * @param element The element to process.
	 * @return The DiffElement or null if no specific diff type exists.
	 */
	public DiffElement process(EObject element){
		return doSwitch(element);
	}
	
	/**
	 * Default switch method.
	 * <strong>You should prefer to use the process() method.</strong>
	 * This one exists only because of the specific type of implementation.
	 */
	@Override
	public DiffElement doSwitch(EObject eObject) {
		return super.doSwitch(eObject);
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
		
		if (unmatchElement.getSide() == Side.RIGHT) {
			// add ImportInsert
			final ImportInsert importInsert = Java2KDMDiffFactory.eINSTANCE
					.createImportInsert();
			importInsert.setImportRight((ImportDeclaration) element);
			importInsert.setRemote(unmatchElement.isRemote());
			return importInsert;
		} else {
			// add ImportDelete
			final ImportDelete importDelete = Java2KDMDiffFactory.eINSTANCE.createImportDelete();
			importDelete.setImportLeft((ImportDeclaration) element);
			importDelete.setRemote(unmatchElement.isRemote());
			return importDelete;
		}
	}
}
