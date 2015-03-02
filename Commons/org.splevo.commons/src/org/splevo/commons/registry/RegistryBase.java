package org.splevo.commons.registry;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public abstract class RegistryBase<T> {

    protected final List<T> elements = Lists.newArrayList();

    protected RegistryBase() {
    }

    /**
     * Register a new element.
     * 
     * Note: If a element instance has already been registered, nothing is done.
     * 
     * @param element
     *            The element itself.
     */
    public void registerElement(T element) {
        if (isValid(element) && isNotRegistered(element)) {
            elements.add(element);
        }
    }

    /**
     * Get the list of registered elements.
     * 
     * @return The current list.
     */
    public List<T> getElements() {
        sortElements();
        return elements;
    }

    protected boolean isValid(T element) {
        if (element == null) {
            return false;
        }
        return true;
    }

    private boolean isNotRegistered(T givenElement) {
        for (T existingElement : elements) {
            if (areElementsConsideredTheSame(existingElement, givenElement)) {
                return false;
            }
        }
        return true;
    }

    private void sortElements() {
        Collections.sort(elements, new Comparator<T>() {
            @Override
            public int compare(T d1, T d2) {
                return compareElements(d1, d2);
            }
        });
    }

    /**
     * Compares the given elements for sorting the elements. The default implementation does not
     * define any order, therefore no sorting takes place.
     * 
     * @param element1
     *            The first element.
     * @param element2
     *            The second element.
     * @return Comparison result according to regular Java compare functions.
     */
    protected int compareElements(T element1, T element2) {
        return 0;
    }

    /**
     * Detects equal elements for finding already registered elements. The default implementation
     * uses equals() to compare the elements.
     * 
     * @param element1
     *            The first element.
     * @param element2
     *            The second element.
     * @return True if the elements are considered equal, False otherwise.
     */
    protected boolean areElementsConsideredTheSame(T element1, T element2) {
        return element1.equals(element2);
    }

}
