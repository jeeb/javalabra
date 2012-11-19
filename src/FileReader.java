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
     * Takes in a string that is then interpreted as a Path, and then returns a
     * BufferedReader object for further reading of the file.
     *
     * Returns null if something failed.
     * @param file_path_string a String that will be used as the path
     * @return A BufferedReader object if successful, otherwise returns null.
     */
    public static BufferedReader openFile(String file_path_string) {
        /* Set a semi-sane character set for reading the file */
        Charset charset = Charset.forName("UTF-8");

        /* Try to read the passed string as a file path */
        Path file_path = Paths.get(file_path_string);

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
     * that is then returned. If it fails, the function returns null.
     *
     * @param reader a BufferedReader object that will be read in the function
     * @return A WordPairContainer object if successful and the file contains entries, otherwise returns null.
     * @throws IOException
     */
    public static WordPairContainer parseFileToWPC(BufferedReader reader) throws IOException {
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
}
