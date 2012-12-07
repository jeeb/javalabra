import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Contains convenience functions for standard input reading
 */
public class StdinReader {
    // We shall read in lolUTF-8
    private static Charset charset = Charset.forName("UTF-8");

    // We also have a BufferedReader here
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, charset));

    /**
     * Reads a single line from the command line (stdin) (aka 'until the next newline character').
     *
     * @return a String object with the contents of the read line if successful, null otherwise
     */
    public static String readLine() {
        String string = null;

        try {
            string = reader.readLine();
            if( string == null ) {
                return null;
            }

            return string;
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return null;
        }
    }

    /**
     * Reads a single line from the command line (stdin) (aka 'until the next newline character'), and parses it into
     * an Integer object.
     *
     * @return an Integer object with the contents of the parsed read line if successful, null otherwise
     */
    public static Integer readInteger() {
        String string = null;
        int    i      = -1;

        try {
            string = reader.readLine();
            if( string == null ) {
                return null;
            }

            i = Integer.parseInt(string, 10);

            return i;
        } catch (IOException e) {
            System.err.println(":: Error reading input:\n\t" + e);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
