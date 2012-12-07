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
            System.err.println(":: Error reading input:\n\t" + e);
            return null;
        }
    }
}
