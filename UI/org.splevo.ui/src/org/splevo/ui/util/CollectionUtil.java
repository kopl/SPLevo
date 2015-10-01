package org.splevo.ui.util;

import java.util.Collection;

import org.splevo.ui.sourceconnection.helper.FileWithID;

/**
 * This static class offers various collection utility functions.
 * 
 * @author André Wengert
 */
public final class CollectionUtil {
    /** The Logger instance */
    //private static final Logger LOGGER = Logger.getLogger(CollectionUtil.class);
    
    /**
     * Constructs an instance of class {@link CollectionUtil}.
     */
    private CollectionUtil() {
    }
    
    /**
     * Checks if a given collection of strings already contains a given string.
     * 
     * @param stringToCheck
     *            the given string.
     * @param collection
     *            the given collection of type {@link String}.
     * @return {@code true} in case the collection contains the given string, {@code false}
     *         otherwise.
     */
    public static boolean containsString(String stringToCheck, Collection<String> collection) {
        for (String item : collection) {
            if (item.equals(stringToCheck)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a given collection of files with identifiers already contains a file with the given
     * identifier.
     * 
     * @param id
     *            the given identifier.
     * @param collection
     *            the collection of instances of {@link FileWithID}.
     * @return {@code true} in case the collection contains a file with the given identifier,
     *         {@code false} otherwise.
     */
    public static boolean containsFileWithId(String id, Collection<FileWithID> collection) {
        for (FileWithID fileID : collection) {
            if (fileID.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }




    
}
