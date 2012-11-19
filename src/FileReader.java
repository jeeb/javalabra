import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 19.11.2012
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class FileReader {
    /**
     * Takes in a Path object, and then returns a BufferedReader object for further reading of the file.
     *
     * Returns null if something failed.
     * @param file_path a Path object that will be used for opening of the BufferedReader
     * @return A BufferedReader object if successful, otherwise returns null.
     */
    public static BufferedReader createBReader(Path file_path) {
        /* Set a semi-sane character set for reading the file */
        Charset charset = Charset.forName("UTF-8");

        try {
            return Files.newBufferedReader(file_path, charset);
        } catch (IOException ioe) {
            /* If le file opening failed, we print an error and return null */
            System.err.println("Error during opening file reader:\n\t" + ioe);
            return null;
        }
    }

    /**
     * Takes in a BufferedReader object that is then used for reading the file and creating a WordPairContainer,
     * that is then returned.
     *
     * Returns null if something fails, or if the file contains no entries.
     *
     * Can throw an IOException if the file reading goes awry, this must be handled on caller's side.
     * @param reader a BufferedReader object that will be read in the function
     * @return A WordPairContainer object if successful and the file contains entries, otherwise returns null.
     * @throws IOException
     */
    public static WordPairContainer parseBReaderToWPC(BufferedReader reader) throws IOException {
        String            line = null;
        WordPairContainer wpc  = new WordPairContainer();

        /* Try reading the first line of the file */
        if ( (line = reader.readLine()) == null ) {
            System.err.println("Error: EOF before magic");
            return null;
        }

        if ( !line.equals("WORDFILE") ) {
            System.err.println("Error: Word file magic not found");
            return null;
        }

        /* If we got here, we have the WORDFILE magic, and we could read the file */

        /* Try reading the second line of the file */
        if ( (line = reader.readLine()) == null ) {
            System.err.println("Error: EOF before file spec version");
            return null;
        }

        /* Try parsing an integer out of the read line */
        int file_version = Integer.parseInt(line, 10);

        /* Current file structure version is 1 */
        if ( file_version != 1 ) {
            System.err.println("Error: File structure version is incorrect");
            return null;
        }

        /* If we got here, we have the file header read */

        int curline = 2; // current line number

        /* Start reading data lines from the word file until EOF */
        while( (line = reader.readLine()) != null ) {
            curline++;

            String[] cutparts = line.split("<>", 3);

            if( cutparts.length < 2 ) {
                System.err.println("Warning: File has less than two delimited parts on line " + curline + ". " +
                        "Ignoring.");
                continue;
            }

            if( cutparts[0].equalsIgnoreCase("") || cutparts[1].equalsIgnoreCase("") ) {
                System.err.println("Warning: File has an empty string before or after delimiter on line " +
                        curline + ". Ignoring.");
                continue;
            }

            if( cutparts.length > 2 ) {
                System.err.println(cutparts[0] + " " + cutparts[1] + " " + cutparts[2]);
                wpc.addWordPair(cutparts[0], cutparts[1], cutparts[2]);
            } else {
                System.err.println(cutparts[0] + " " + cutparts[1]);
                wpc.addWordPair(cutparts[0], cutparts[1]);
            }
        }

        /* If the WordPairContainer is not empty, return it. Otherwise return null */
        if( !wpc.isEmpty() ) {
            return wpc;
        } else {
            return null;
        }
    }

    /**
     * Takes in a String that is then used as a Path to have a file parsed as a Word Pair file.
     * @param file_path_string a String that is supposed to be a path towards a file that can be read
     * @return A WordPairContainer object if successful, otherwise returns null.
     */
    public static WordPairContainer createWPCFromFile(String file_path_string) {
        Path              file_path = null;
        BufferedReader    br        = null;

        /* Try to read the passed string as a file path */
        file_path = Paths.get(file_path_string);

        /* If the Path still points to null, we have an error state */
        if( file_path == null ) {
            System.err.println("Error state happened when creating the reader object!");
            return null;
        }

        /* Try to create a BufferedReader from the Path */
        br = createBReader(file_path);

        /* If the BufferedReader still points to null, we have an error state */
        if( br == null ) {
            System.err.println("Error state happened when reading the file!");
            return null;
        }

        /* Try parsing the file for which we got a BufferedReader into a WordPairContainer */
        try {
            return parseBReaderToWPC(br);
        } catch (IOException ioe) {
            /* If we got an exception, we have a fail */
            System.err.println("I/O Error during file parsing:\n\t" + ioe);
            return null;
        }
    }
}
