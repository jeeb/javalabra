import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implements the stringification and file writing features needed to write WordPairContainer objects onto a Path.
 */
public class FileWriter {

    /**
     * Creates A BufferedWriter object with the given Path object, using the UTF-8 character set.
     * @param file_path The Path given to create the BufferedWriter against
     * @return A BufferedWriter object if successful, null otherwise.
     */
    private static BufferedWriter createBWriter(Path file_path) {
        /* Set a semi-sane character set for writing the file */
        Charset charset = Charset.forName("UTF-8");

        try {
            return Files.newBufferedWriter(file_path, charset);
        } catch (IOException ioe) {
            /* If le file opening failed, we print an error and return null */
            System.err.println("Error during opening file writer:\n\t" + ioe);
            return null;
        }
    }

    /**
     * Writes the given String object to the given BufferedWriter object.
     *
     * @param bw The BufferedWriter object that is to be used when writing the string.
     * @param string_to_be_written The String object that is to be written to the BufferedWriter object.
     * @return true if successful, false otherwise.
     */
    private static boolean writeFinalStringToFile(BufferedWriter bw, String string_to_be_written) {
        /* Try writing */
        try {
            bw.write(string_to_be_written, 0, string_to_be_written.length());
            bw.close();
        } catch (IOException ioe) {
            /* If le file writing failed, we print an error and return null */
            System.err.println("Error during writing the string to the file:\n\t" + ioe);
            return false;
        }

        /* If the write succeeded without exceptions, we return true */
        return true;
    }

    /**
     * Stringifies the given WordPair into the given StringBuilder accordingly to the WordPair file specification.
     * @param wp The WordPair object that is to be used as the source of data.
     * @param sb The StringBuilder object into which the data shall be added.
     * @return true if successful, false otherwise.
     */
    private static boolean stringifyWordPair(WordPair wp, StringBuilder sb) {
        if( wp == null ) {
            System.err.println("Error: WP given was null!");
            return false;
        }

        sb.append(wp.getWord());
        sb.append("<>");
        sb.append(wp.getPair());
        sb.append("<>");
        sb.append(wp.getComment());
        sb.append("\n");

        return true;
    }

    /**
     * Creates a String from the given WordPairContainer, that then can be written into a file with the
     * writeFinalStringToFile function.
     *
     * If successful, returns a String object. Otherwise returns null.
     * @param wpc The WordPairContainer to be Stringified
     * @return a String object if successful, null if an error happened
     */
    private static String stringifyWordPairContainer(WordPairContainer wpc) {
        /* Initialize variables */
        WordPair wp              = null;

        /* Create a new StringBuilder */
        StringBuilder sb = new StringBuilder();

        /* Check for null */
        if( wpc == null ) {
            System.err.println("Error: WPC given was null!");
            return null;
        }

        /* Check for emptiness */
        if( wpc.isEmpty() ) {
            System.err.println("Error: WPC is empty, will not stringify an empty WPC!");
            return null;
        }

        /* Add the usual header to the beginning of the file */
        sb.append("WORDFILE\n");
        sb.append("1\n");

        for( int i = 0; i < wpc.getWordPairCount(); i++ ) {
            wp = wpc.getWordPair(i);

            if( wp == null ) {
                System.err.println("Error: Gotten word pair was null!");
                return null;
            }

            if( !stringifyWordPair(wp, sb) ) {
                System.err.println("Error: Stringification of a word pair failed!");
                return null;
            }
        }

        return sb.toString();
    }

    /**
     * Tries to write the contents of the given WordPairContainer into a file specified by a String that is then
     * parsed as a Path.
     *
     * Returns true if successful, false otherwise.
     * @param wpc The WordPairContainer to be written
     * @param file_path_string The String containing the path to the file to be written to
     * @return true if successful, false otherwise
     */
    public static boolean writeWordPairContainerToFile(WordPairContainer wpc, String file_path_string) {
        /* Initialize the variables */
        Path           file_path       = null;
        BufferedWriter bw              = null;
        String         string_to_write = null;

        if( wpc == null || file_path_string == null ) {
            System.err.println("Error: The WPC or string given to writer was null!");
            return false;
        }

        file_path = Paths.get(file_path_string);

        if( file_path == null ) {
            System.err.println("Error: File path gotten from string was null!");
            return false;
        }

        bw = createBWriter(file_path);

        if( bw == null ) {
            System.err.println("Error: Creating a buffered writer failed!");
            return false;
        }

        string_to_write = stringifyWordPairContainer(wpc);

        if( string_to_write == null ) {
            System.err.println("Error: Creating the string failed!");
            return false;
        }

        return writeFinalStringToFile(bw, string_to_write);
    }
}
