package org.splevo.fm.builder;

/**
 * This is a wrapper class for feature models.
 * 
 * @param <Model>
 *            The model which is to be wrapped.
 */
public class FeatureModelWrapper<Model> {

    private boolean canBeDisplayed;
    private Model model;

    /**
     * Initializes the wrapper with a given model and a tag indicating whether the
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
