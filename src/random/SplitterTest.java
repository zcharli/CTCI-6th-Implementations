package random;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * @author cli
 * @since 11/29/18
 */
public class SplitterTest
{
    public static void main(String[] args)
    {
        final Pattern metaLineSplitter = Pattern.compile("\\[|\\]");
        
        String metaData = "[29 Nov 2018 09:34:33,751]";
        List<String> tokens = metaLineSplitter.splitAsStream(metaData).map(String::trim).filter(s -> !s.isEmpty()).collect(toList());
    
        System.out.println(tokens.size());
    }
}
