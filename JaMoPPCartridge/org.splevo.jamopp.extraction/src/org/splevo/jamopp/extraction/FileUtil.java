/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.extraction;

import java.io.File;
import java.util.ArrayList;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * Utility class to work with files, directories and file systems.
 */
public final class FileUtil {

    /**
     * Get the last segment of a file directory path.
     *
     * @param path
     *            The absolute path.
     * @return The last segment found. Null if no segment is available at all.
     */
    public static String getLastSegment(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        Iterable<String> split = Splitter.on(File.separator).trimResults().omitEmptyStrings().split(path);
        ArrayList<String> segments = Lists.newArrayList(split);
        String lastSegment = segments.get(segments.size() - 1);
        return lastSegment;
    }
}
