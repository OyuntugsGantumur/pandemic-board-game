

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CityTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CityTest
{
    City test;
    
    /**
     * Default constructor for test class CityTest
     */
    public CityTest()
    {
        test = new City("Ulaanbaatar", new Disease("pastel"));
    }

    @Test
    public void testAddRemove() {
        test.addCube(3, "test");
        assertTrue(3 == test.numCube);
        assertTrue(false == test.outbreak);
        
        test.addCube(2, "test");
        assertTrue(3 == test.numCube);
        assertTrue(true == test.outbreak);
        
        test.removeCube();
        assertTrue(2 == test.numCube);
        
        test.removeAllCube();
        assertTrue(0 == test.numCube);
    }
}
