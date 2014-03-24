package org.splevo.refactoring;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.emftext.refactoring.interpreter.AbstractValueProvider;
import org.emftext.refactoring.interpreter.IAttributeValueProvider;
import org.emftext.refactoring.interpreter.IRefactoringFakeInterpreter;
import org.emftext.refactoring.interpreter.IRefactoringInterpreter;

public class AttributeValueProvider extends AbstractValueProvider<EAttribute, Object> implements
        IAttributeValueProvider {

    private EAttribute attribute;
    private EObject fakeAttributeOwner;
    private EObject realAttributeOwner;
    private EAttribute fakeAttribute;
    private EAttribute realAttribute;

    @Override
    public EAttribute getAttribute() {
        return attribute;
    }

    @Override
    public EObject getAttributeOwner() {
        return realAttributeOwner;
    }

    @Override
    public EObject getFakeAttributeOwner() {
        return fakeAttributeOwner;
    }

    @Override
    public boolean isValueValid(String text) {
        if (text != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object getFakeObject() {
        return fakeAttribute;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getReturnCode() {
        // doesn't matter
        return 0;
    }

    @Override
    public void propagateValueToFakeObject() {
        if (getValue() != null) {
            fakeAttributeOwner.eSet(fakeAttribute, getValue());
        }
    }

    @Override
    public Object provideValue(IRefactoringInterpreter interpreter, EAttribute from, Object... context) {
        if (interpreter instanceof IRefactoringFakeInterpreter) {
            fakeAttribute = attribute;
            fakeAttributeOwner = (EObject) context[0];
        }
        attribute = from;
//        setValue(method);
        return getValue();
    }

    public void provideValue() {
        realAttributeOwner = getInverseCopier().get(fakeAttributeOwner);
        if (realAttributeOwner != null) {
            realAttribute = (EAttribute) realAttributeOwner.eClass().getEStructuralFeature(fakeAttribute.getName());
            attribute = realAttribute;
        }
    }
}
