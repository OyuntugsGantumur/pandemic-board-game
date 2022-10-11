
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MapTest
{
    Map test1;
    Map test2;
    Map test3;
    Map test4;
    Reader reader;
    Random rand;
    
    /**
     * Default constructor for test class MapTest
     */
    public MapTest()
    {
        reader = new Reader("test.txt");
        rand = new Random();
        test1 = new Map(reader, "test.txt", 1, rand);
        test2 = new Map(reader, "test.txt", 2, rand);
        test3 = new Map(reader, "test.txt", 3, rand);
        test4 = new Map(reader, "test.txt", 4, rand);
    }

    @Test
    public void test() {
        System.out.println("------------");
        test1.createMap(1);
        System.out.println(test1.toString());
       
        System.out.println("------------");
        test2.createMap(2);
        System.out.println(test2.toString());
        
        System.out.println("------------");
        test3.createMap(3);
        System.out.println(test3.toString());
        
        System.out.println("------------");
        test4.createMap();
        System.out.println(test4.toString());
    }
    
    @Test
    public void testCity() {
        test1.createMap(2);
        assertEquals("Tokyo", test1.findCity("Tokyo").toString());
    }
}
