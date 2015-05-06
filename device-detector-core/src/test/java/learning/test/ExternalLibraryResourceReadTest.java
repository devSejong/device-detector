package learning.test;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;

public class ExternalLibraryResourceReadTest {
    @Test
    public void 외부라이브러리_파일읽기_테스트() throws URISyntaxException, FileNotFoundException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/META-INF/spring.factories");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
