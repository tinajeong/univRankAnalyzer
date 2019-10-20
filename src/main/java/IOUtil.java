package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IOUtil {
    public IOUtil() {
    }

    public void testIOUtil() throws IOException {
        InputStream in = new URL( "http://commons.apache.org" ).openStream();
        try {
            InputStreamReader inR = new InputStreamReader( in );
            BufferedReader buf = new BufferedReader( inR );
            String line;
            while ( ( line = buf.readLine() ) != null ) {
                System.out.println( line );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}
