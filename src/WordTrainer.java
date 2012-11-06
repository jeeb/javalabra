import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jeekstro
 * Date: 29.10.2012
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class WordTrainer {

    public static FileReader openFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        return fr;
    }

    /* Main function for testing purposes */
    public static void main(String[] args) {
        /* Check for argument count */
        if(args.length < 1) {
            System.out.println("Give at least one argument");
        }

        System.out.println("File name set: " + args[0]);

        try( FileReader fr = openFile(args[0]) ) {

        } catch(IOException ioe) {
            System.out.println("Error happened: " + ioe.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}
