package random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cli
 * @since 7/25/18
 */
public class HashTest
{
    public static void main(String[] args)
    {
        System.out.println(Collections.singletonList(2).hashCode()); // prints 33
        List<Integer> l = new ArrayList<>();
        l.add(2);
        System.out.println(l.hashCode()); // prints 33
    
        l = new ArrayList<>();
        l.add(5);
        l.add(6);
        System.out.println(l.hashCode()); // prints 1122
        l = new ArrayList<>();
        l.add(5);
        l.add(6);
        System.out.println(l.hashCode()); // prints 1122
    
    }
}
