import java.util.*;

/**
 * Represents a deck of cards 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    LinkedList<Card> deck;
    Random random;

    /**
     * Constructor for objects of class Deck
     */
    public Deck(Random random)
    {
        deck = new LinkedList<Card>();
        this.random = random;
    }
    
    /**
     * Adds the given card to the deck
     */
    public void add(Card card) {
        deck.add(card);
    }
    
    /**
     * Returns the card at the given index
     */
    public Card get(int index) {
        return deck.get(index);
    }
    
    /**
     * Returns and removes the top card of the deck
     */
    public Card remove() {
        return deck.remove();
    }
    
    public void clear() {
        deck.clear();
    }

    /**
     * Shuffles the deck of cards
     * 
     * @return LinkedList<Card> shuffled deck of cards
     */
    public LinkedList<Card> shuffle() {
        LinkedList<Card> shuffled = new LinkedList<Card>();
        int size = deck.size();
        int pos = random.nextInt(size);
        
        //randomly chooses cards and adds them to the new list
        for(int i = 0; i < deck.size(); i++) {            
            
            if(shuffled.contains(deck.get(pos)) == false) {
                shuffled.add(deck.get(pos));
            }
            
            pos = random.nextInt(size);
        }
        
        //adds the cards not chosen by the random generator
        for(int i = 0; i < deck.size(); i++) {
            
            if(shuffled.contains(deck.get(i)) == false) {
                shuffled.add(deck.get(i));
            }
            
        }
        
        deck = shuffled;
        
        return deck;
    }
    
    /**
     * Converts the deck with epidemic cards to a string
     */
    public String toString() {
        String ret = "";       
        
        for(int i = 0; i < deck.size(); i++) {
            
            if(deck.get(i).isEpidemic == true) {
                ret += "Epidemic card, ";
            } 
            else {            
                ret += deck.get(i).toString() + ", ";
            }
        }
        
        return ret;
    }

}
