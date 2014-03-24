package org.splevo.refactoring;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.emftext.refactoring.interpreter.IValueProvider;
import org.emftext.refactoring.interpreter.IValueProviderFactory;

public class ValueProviderFactory implements IValueProviderFactory {

    private AttributeValueProvider attributeValueProvider = new AttributeValueProvider();

    @Override
    public IValueProvider<?, ?> getValueProviderForCommand(EObject command, Object... context) {
        return null;
    }

    @Override
    public void registerValueProviderForCommand(EObject command, IValueProvider<?, ?> valueProvider) {
        // nothing to do here
    }

    @Override
    public IValueProvider<?, ?> registerValueProviderForCommand(EObject command, Object... context) {
        return null;
    }

    @Override
    public List<IValueProvider<?, ?>> getValuesToProvide() {
        return null;
    }

}
