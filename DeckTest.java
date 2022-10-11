
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DeckTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DeckTest
{
    Deck test;
    Card card1;
    Card card2;
    Card card3;
    Random rand;
    
    /**
     * Default constructor for test class DeckTest
     */
    public DeckTest()
    {
        rand = new Random();
        test = new Deck(rand);        
        card1 = new Card(new Disease("black"), "New York");
        card2 = new Card(new Disease("blue"), "Easton");
        card3 = new Card(new Disease("green"), "Tokyo");
        
    }
    
    @Test
    public void test() {
        test.add(card1);
        test.add(card2);
        test.add(card3);
        test.add(new Card(true));
        assertEquals("New York(black), Easton(blue), Tokyo(green), Epidemic card, ", test.toString());
        
        test.remove();
        test.remove();
        assertEquals("Tokyo(green), Epidemic card, ", test.toString());
        
        test.clear();
        assertEquals("", test.toString());        
        
    }
    
    @Test
    public void testShuffle() {
        test.add(card1);
        test.add(card2);
        test.add(card3);
        test.add(new Card(true));        
        test.add(new Card(new Disease("yellow"), "Washington"));        
        test.add(new Card(new Disease("blue"), "Seoul"));
        test.add(new Card(new Disease("black"), "Paris"));
        test.add(new Card(new Disease("yellow"), "Milan"));
        
        test.shuffle();
        System.out.println(test.toString());
    }

}
