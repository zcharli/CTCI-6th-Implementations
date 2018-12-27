package random;

import java.util.LinkedHashMap;

/**
 * @author cli
 * @since 12/5/18
 */
public class LinkedHashMapTest
{
    public static void main(String[] args)
    {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        map.put(4,1);
        map.put(5,1);
        map.put(6,1);
    
        map.replace(2,2);
        System.out.println(map);
    }
}
