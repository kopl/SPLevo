package org.splevo.ui.sourceconnection;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.splevo.ui.sourceconnection.helper.FileWithID;
import org.splevo.ui.sourceconnection.helper.IndexedLineNumber;
import org.splevo.ui.sourceconnection.helper.NumbersTextPair;

/**
 * This singleton class offers functionality to read and write files from and to the used data
 * structure.
 * 
 * @author André Wengert
 */
public final class UnifiedDiffFileHandler {
    /** The Logger instance */
    private static final Logger LOGGER = Logger.getLogger(UnifiedDiffFileHandler.class);
    /** The class instance */
    private static UnifiedDiffFileHandler instance = null;

    /**
     * Constructs an instance of class {@link UnifiedDiffFileHandler}.
     */
    private UnifiedDiffFileHandler() {
    }

    /**
     * Reads the leading file content to the used line data structure.
     * 
     * @param leadingFile
     *            the leading file to read.
     * @return the list of line data structures {@link NumbersTextPair}.
     */
    public List<NumbersTextPair> readLeadingFileToData(FileWithID leadingFile) {
        List<NumbersTextPair> leadingData = new ArrayList<NumbersTextPair>();
        try {
            String[] leadingContent = readFileToLines(leadingFile.getFile());
            for (int i = 0; i < leadingContent.length; i++) {
                List<IndexedLineNumber> lineNumbers = new ArrayList<IndexedLineNumber>();
                lineNumbers.add(new IndexedLineNumber(0, i + 1));
                leadingData.add(new NumbersTextPair(lineNumbers, leadingContent[i]));
            }
        } catch (IOException exception) {
            LOGGER.error("An error occured while reading leading file \"" + leadingFile.getFile().getAbsolutePath()
                    + "\"", exception);
        }

        return leadingData;
    }

    /**
     * Reads the integration copies' content to a list of {@code String}-arrays.
     * 
     * @param integrationFiles
     *            the list of integration copies.
     * @return the list of integration copies represented by arrays of <code>String</code>.
     */
    public List<String[]> readIntegrationCopies(List<FileWithID> integrationFiles) {
        List<String[]> integrationCopiesAsArray = new ArrayList<String[]>();
        for (FileWithID integrationCopy : integrationFiles) {
            String[] integrationCopyTextAsArray = null;
            try {
                integrationCopyTextAsArray = readFileToLines(integrationCopy.getFile());
            } catch (IOException e) {
                LOGGER.error("An error occured while reading integration copy \""
                        + integrationCopy.getFile().getAbsolutePath() + "\"", e);
            }

            integrationCopiesAsArray.add(integrationCopyTextAsArray);
        }

        return integrationCopiesAsArray;
    }

    /**
     * Reads the file contents of a given file as an array of string lines.
     * 
     * @param file
     *            the file to process
     * @return the string array containing all text lines
     * @throws IOException
     *             in case an I/O error occurs while reading the given file.
     */
    private String[] readFileToLines(File file) throws IOException {
        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
        // FIXME: Implement more memory friendly method (i.e. mapping lines to Unicode characters)
        return fileContent.split("\\r?\\n");
    }

    /**
     * Writes the unified difference text lines to the given file.
     * 
     * @param unifiedTextLines
     *            the unified difference lines to write.
     * @param toFile
     *            the given file to write to.
     * @throws IOException
     *             in case the writing process fails.
     */
    public void writeUnifiedLines(List<String> unifiedTextLines, File toFile) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String string : unifiedTextLines) {
            builder.append(string + "\n");
        }
        FileUtils.write(toFile, builder.toString(), StandardCharsets.UTF_8.name());
    }

    /**
     * Gets the only instance of the unified difference file handler.
     * 
     * @return the file handler instance.
     */
    public static synchronized UnifiedDiffFileHandler getInstance() {
        if (instance == null) {
            instance = new UnifiedDiffFileHandler();
        }
        return instance;
    }
}
