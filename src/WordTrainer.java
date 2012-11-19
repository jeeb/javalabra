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

        WordPairContainer wpc = null;

        /* Check for argument count */
        if( args.length < 1 ) {
            System.err.println("Error: Give at least one argument");
            System.exit(1);
        }

        /* Print the file name used for debug purposes, and create the path */
        System.err.println("File name set: " + args[0]);

        wpc = FileReader.createWPCFromFile(args[0]);

        /* Error state happened and null was returned */
        if( wpc == null ) {
            System.err.println("Error state happened, exiting program");
            System.exit(1);
        }

        System.exit(0);
    }
}
