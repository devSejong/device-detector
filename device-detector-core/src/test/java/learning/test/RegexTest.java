package learning.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    @Test
    public void getVersion(){
        String mydata = "Android 4.2.2-snapshot";
        Pattern pattern = Pattern.compile("Android ([\\d\\.]+)");
        Matcher matcher = pattern.matcher(mydata);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
