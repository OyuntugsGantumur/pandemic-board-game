

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DiseaseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiseaseTest
{
    Disease test;
    
    /**
     * Default constructor for test class DiseaseTest
     */
    public DiseaseTest()
    {
        test = new Disease("purple");
    }

    @Test
    public void test() {
        test.incrCount(2);
        test.incrCount(1);
        assertTrue(3 == test.count);
        
        test.decrCount();
        test.decrCount();        
        assertTrue(1 == test.count);
        
        test.cure();
        assertTrue(true == test.cured);
        
        test.decrCount();
        assertTrue(true == test.eradicated);
    }
}
