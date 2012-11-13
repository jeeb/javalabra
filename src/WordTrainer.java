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

    public static WordPairContainer parseWordPairFile(Path file_path) {
        /* Set a semi-sane character set for input/output */
        Charset charset = Charset.forName("UTF-8");

        String            line = null;
        WordPairContainer wpc  = new WordPairContainer();

        /* Try opening the file */
        try( BufferedReader reader = Files.newBufferedReader(file_path, charset) ) {
            /* Try reading the first line of the file */
            if( (line = reader.readLine()) == null ) {
                System.err.println("Error: EOF before magic");
                return null;
            }

            if( !line.equals("WORDFILE") ) {
                System.err.println("Error: Word file magic not found");
                return null;
            }

            /* If we got here, we have the WORDFILE magic, and we could read the file */

            /* Try reading the second line of the file */
            if( (line = reader.readLine()) == null ) {
                System.err.println("Error: EOF before file spec version");
                return null;
            }

            /* Try parsing an integer out of the read line */
            int file_version = Integer.parseInt(line, 10);

            /* Current file structure version is 1 */
            if( file_version != 1 ) {
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

            System.err.println("Count of pairs in wordmap: " + wpc.getWordPairCount());

        } catch( IOException ioe ) {
            /* If le file opening failed, we print an error and exit */
            System.err.println("Error during file I/O:\n\t" + ioe);
            return null;
        } catch( NumberFormatException nfe ) {
            /* If we have a fail with parsing an integer from the file header, we give an error and exit */
            System.err.println("Error during parsing an integer from a string:\n\t" + nfe);
            return null;
        }

        return wpc;
    }

    /**
     * Main function (for testing currently)
     * @param args command line arguments given
     */
    public static void main(String[] args) {
        /* Set a semi-sane character set for input/output */
        Charset charset = Charset.forName("UTF-8");

        WordPairContainer wpc  = null;

        /* Check for argument count */
        if( args.length < 1 ) {
            System.err.println("Error: Give at least one argument");
            System.exit(1);
        }

        /* Print the file name used for debug purposes, and create the path */
        System.err.println("File name set: " + args[0]);

        wpc = parseWordPairFile(Paths.get(args[0]));

        /* Error state happened and null was returned */
        if( wpc == null ) {
            System.err.println("Error state happened, exiting program");
            System.exit(1);
        }

        System.exit(0);
    }
}
