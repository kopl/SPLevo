package org.splevo.fm.builder;

import org.eclipse.featuremodel.FeatureModel;

/**
 * This is a wrapper class for {@link FeatureModel}s.
 * 
 * @param <Model>
 *            The model which is to be wrapped.
 */
public class FeatureModelWrapper<Model extends FeatureModel> {

    private boolean canBeDisplayed;
    private Model model;

    /**
     * Initializes the wrapper with a given {@link FeatureModel} and a tag indicating whether the
     * model can be shown in the UI.
     * 
     * @param model
     *            The {@link FeatureModel}.
     * @param canBeDisplayed
     *            <code>true</code> if model can be displayed; <code>false</code> otherwise.
     */
    public FeatureModelWrapper(Model model, boolean canBeDisplayed) {
        this.model = model;
        this.canBeDisplayed = canBeDisplayed;
    }

    /**
     * @return the canBeDisplayed
     */
    public boolean isCanBeDisplayed() {
        return canBeDisplayed;
    }

    /**
     * @param canBeDisplayed
     *            the canBeDisplayed to set
     */
    public void setCanBeDisplayed(boolean canBeDisplayed) {
        this.canBeDisplayed = canBeDisplayed;
    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

}
