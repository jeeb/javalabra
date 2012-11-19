import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 19.11.2012
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class FileWriter {
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

    private static boolean writeFinalStringToFile(BufferedWriter bw, String string_to_be_written) {
        /* Try writing */
        try {
            bw.write(string_to_be_written, 0, string_to_be_written.length());
        } catch (IOException ioe) {
            /* If le file writing failed, we print an error and return null */
            System.err.println("Error during writing the string to the file:\n\t" + ioe);
            return false;
        }

        /* If the write succeeded without exceptions, we return true */
        return true;
    }

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

    public static boolean WriteWordPairContainerToFile(WordPairContainer wpc, String file_path_string) {
        /* Initialize the variables */
        Path           file_path = null;
        BufferedWriter bw        = null;

        if( wpc == null ) {
            System.err.println("Error: The WPC given to writer was null!");
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

        return writeFinalStringToFile(bw, stringifyWordPairContainer(wpc));
    }
}
