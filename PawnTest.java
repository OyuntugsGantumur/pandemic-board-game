
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PawnTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PawnTest
{
    Pawn pawn1;
    Pawn pawn2;
    Map board;
    Reader reader;
    Random rand;
    
    /**
     * Default constructor for test class PawnTest
     */
    public PawnTest()
    {
        reader = new Reader("test.txt");
        rand = new Random();
        board = new Map(reader, "test.txt", 2, rand);
        board.createMap(2);
        pawn1 = new Pawn(board, board.map.keySet().iterator().next(), rand);
        pawn2 = new Pawn(board, board.map.keySet().iterator().next(), rand);
    }

    @Test
    public void test1() {
        System.out.println(board.toString());
        board.findCity("NewYork").addCube(3, "test");
        board.findCity("Stockholm").addCube(3, "test");
        board.findCity("Moscow").addCube(2, "test");
        board.findCity("Washington").addCube(2, "test");
        board.findCity("Tokyo").addCube(1, "test");
        board.findCity("Paris").addCube(1, "test");
        
        int i = 0;
        while(i < 3) {
            pawn1.behave(1, 1);
            System.out.println("---------");
            pawn2.behave(2, 2);
            System.out.println("---------");
            i++;
        }
        
        System.out.println("---------");
    }
    
    @Test
    public void test2() {
        System.out.println(board.toString());
        board.findCity("NewYork").addCube(3, "test");
        board.findCity("Stockholm").addCube(3, "test");
        board.findCity("Moscow").addCube(2, "test");
        board.findCity("Washington").addCube(2, "test");
        board.findCity("Tokyo").addCube(1, "test");
        board.findCity("Paris").addCube(1, "test");
        
        pawn2.behave(2, 2);
        pawn2.behave(2, 2);
        
        System.out.println("---------");
    }
    
}
