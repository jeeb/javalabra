import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.lang.Integer;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 29.10.2012
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class WordTrainer {

    /**
     * Main function (for testing currently)
     * @param args command line arguments given
     */
    public static void main(String[] args) {
        /* Set a semi-sane character set for input/output */
        Charset charset = Charset.forName("UTF-8");

        String line = null;

        /* Check for argument count */
        if( args.length < 1 ) {
            System.err.println("Error: Give at least one argument");
            System.exit(1);
        }

        /* Print the file name used for debug purposes, and create the path */
        System.err.println("File name set: " + args[0]);
        Path file_path = Paths.get(args[0]);

        /* Try opening the file */
        try( BufferedReader reader = Files.newBufferedReader(file_path, charset) ) {
            /* Try reading the first line of the file */
            if( (line = reader.readLine()) == null ) {
                System.err.println("Error: EOF before magic");
                System.exit(1);
            }

            if( !line.equals("WORDFILE") ) {
                System.err.println("Error: Word file magic not found");
                System.exit(1);
            }

            /* If we got here, we have the WORDFILE magic, and we could read the file */

            /* Try reading the second line of the file */
            if( (line = reader.readLine()) == null ) {
                System.err.println("Error: EOF before file spec version");
                System.exit(1);
            }

            /* Try parsing an integer out of the read line */
            int file_version = Integer.parseInt(line, 10);

            /* Current file structure version is 1 */
            if( file_version != 1 ) {
                System.err.println("Error: File structure version is incorrect");
                System.exit(1);
            }

            /* If we got here, we have the file header read */


        } catch( IOException ioe ) {
            /* If le file opening failed, we print an error and exit */
            System.err.println("Error during file I/O: " + ioe);
            System.exit(1);
        } catch( NumberFormatException nfe ) {
            System.err.println("Error during parsing an integer from a string: " + nfe);
            System.exit(1);
        }

        System.exit(0);
    }
}
