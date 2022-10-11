import java.util.*;

/**
 * Represents a pawn
 *
 * @author Oyu Gantumur
 */
public class Pawn
{
    Random random;
    Map board;
    City currCity;
    ArrayList<Card> cardsInHand;
    int actionNum;
    boolean actionType;

    /**
     * Constructor for objects of class Pawn
     */
    public Pawn(Map board, City currCity, Random random)
    {
        this.board = board;
        this.currCity = currCity;
        cardsInHand = new ArrayList<Card>();
        actionNum = 0;
        actionType = true;
        this.random = random;
    }

    /**
     * A generic behave method that defines a specific behavior to each pawn
     * 
     * @param type the number assigned to each pawn
     * @param n the behavior assigned to the pawn
     */
    public void behave(int type, int n) {
        actionNum = 0;   
        int numCube;

        if(n == 1) {
            currCity = compareNeighbors(currCity);
            numCube = currCity.numCube;
            System.out.println("Pawn "+ type + " moved to " + currCity.name);
            actionNum++;
            behavior1(numCube, type);            
        }
        else if(n == 2) {
            behavior2(type);
        }

    }

    /**
     * A behave method for a researcher pawn
     * 
     * @param type the number assigned to each pawn
     * @param n the behavior assigned to the pawn
     * @param Pawn[] pawn - an array of other pawns
     */
    public void behave(int type, int n, Pawn[] pawn) {
        actionNum = 0;
        behavior3(type, pawn);
    }

    /**
     * Specifies the functions of behavior #1 - Disease treater. 
     */
    public void behavior1(int num, int type) {

        for(int j = 0; j < num; j++) {

            if(actionNum < 4 && currCity.hasCube() == true) {
                currCity.removeCube();
                System.out.println("A cube is removed from " + currCity.name);
                actionNum++;
            }

        }

        if(actionNum < 4) {
            currCity = compareNeighbors(currCity);
            System.out.println("Pawn " + type + " moved to " + currCity.name);
            actionNum++;
            behavior1(3, type);
        }
    }

    /**
     * Compares the neighboring cities and 
     * returns the one with the highest number of cubes. 
     * 
     * @param city whose neighbors need to be compared
     * @return the city with the most cubes
     */
    public City compareNeighbors(City comp) {
        LinkedList<City> neighbors = board.map.get(comp);
        City max = neighbors.get(0);

        for(int i = 0; i < neighbors.size(); i++) {

            //finds the city with the highest number of cubes
            if(neighbors.get(i).numCube > max.numCube) {
                max = neighbors.get(i);
            }

        }

        return max;
    }

    /**
     * Specifies the functions of behavior #2 - 50/50. 
     */
    public void behavior2(int type) {        

        //half the time, cures diseases
        if(actionType == true) {

            currCity = compareNeighbors(currCity);
            int numCube = currCity.numCube;
            System.out.println("Pawn " + type + " moved to " + currCity.name);
            actionNum++;

            behavior1(numCube, type);
            actionType = false;

        }        
        else {  //other half the time, moves around to randomly chosen cities            

            while(actionNum < 4) {

                LinkedList<City> neighbors = board.map.get(currCity);
                int loc = random.nextInt(neighbors.size());
                currCity = neighbors.get(loc);
                System.out.println("Pawn " + type + " moved to " + currCity.name);
                actionNum++;

            }

            actionType = true;
        }
    }

    /**
     * Specifies the functions of behavior #3 - Researcher. 
     */
    public void behavior3(int type, Pawn[] pawns) {     

        if(cardsInHand.isEmpty() == false) {           
            Card common = findCommon(cardsInHand);

            for(int i = 0; i < pawns.length; i++) {

                if(cardsInHand.size() >= 3  && common.diseaseType.cured == false) {

                    if(commonCardNum(common, cardsInHand) < 3 && containsCommon(common, pawns[i].cardsInHand)) {
                        /*&& pawns[i].currCity == currCity*/ 
                        swapCards(common, this.cardsInHand, pawns[i].cardsInHand);
                        System.out.println("Pawn " + type + " swapped cards with pawn at " + i);
                        actionNum++;

                    }  
                    else {
                        
                        //if(currCity.hasResearchStation == true) {
                            common.diseaseType.cure();
                            updateHandCards(common, cardsInHand);
                            System.out.println("-----------");
                            System.out.println("Disease " + common.diseaseType.name + " is cured.");
                            actionNum++;
                        //}

                    }
                    
                }
            } 

        }

        if(hasCityCard(cardsInHand, currCity) == true) {

            currCity.buildResStation();
            System.out.println("Pawn " + type + " built a research station at " + currCity.name);
            board.numResStation++;
            actionNum++;

        }
        else {   //if no cards match anything, moves around. 

            LinkedList<City> neighbors = board.map.get(currCity);
            int loc = random.nextInt(neighbors.size());
            currCity = neighbors.get(loc);
            System.out.println("Pawn " + type + " moved to " + currCity.name);
            actionNum++;

        }

        if(actionNum < 3) {
            behavior3(type, pawns);
        }

    }

    /**
     * Finds the common disease type among the cards in hand
     */
    public Card findCommon(ArrayList<Card> list) {        
        Card common = list.get(0);

        int max = 0;

        for(int i = 0; i < list.size(); i++) {
            
            //if the disease is cured, finds the next common card
            if(common.diseaseType.cured == true) {
                common = list.get(i);
            }
            
            Card curr = list.get(i);
            int count = 0;

            //counts the cards with the same disease as the curr
            for(int k = 0; k < list.size(); k++) {
                if(list.get(k).diseaseType == curr.diseaseType) {
                    count++;
                }
            }

            //updates the card with the maximum number
            if(count > max) {
                common = curr;
                max = count;
            }
        }

        return common;
    }

    /**
     * Checks if deck2 contains the common card.
     */
    public boolean containsCommon(Card common, ArrayList<Card> deck2) {

        for(int i = 0; i < deck2.size(); i++) {
            if(deck2.get(i).diseaseType == common.diseaseType) {
                return true;
            }

        }

        return false;    
    }

    /**
     * Returns the maximum number of the same disease cards in the list
     * 
     * @param the list to checked
     */
    public int commonCardNum(Card card, ArrayList<Card> list) {
        int count = 0;

        for(int i = 0; i < list.size(); i++) {

            if(list.get(i).diseaseType == card.diseaseType) {
                count++;
            }

        }

        return count;
    }

    /**
     * Removes the same disease cards from the hand-cards when treating a disease
     * 
     * @param common card to be searched and removed
     * @param list of cards in hand
     */
    public void updateHandCards(Card card, ArrayList<Card> list) {
        int num = 3;

        for(int i = 0; i < list.size(); i++) {
            
            if(list.get(i).diseaseType.name.equals(card.diseaseType.name) && num > 0) {
                list.remove(i);
                num--;
                i--;
            }
            
        }
        
    }

    /**
     * Checks if the cards in hand contain a card with the specified city on it
     * 
     * @param list of cards in hand
     * @param city to be searched for
     * @return if the list contains the specified city
     */
    public boolean hasCityCard(ArrayList<Card> list, City city) {

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).cityName.equals(city.name)) {
                list.remove(i);
                return true;
            }
        }

        return false;        
    }

    /**
     * Swaps cards - deck1 gets the common card and gives an useless card in return
     */
    public ArrayList<Card> swapCards(Card card, ArrayList<Card> deck1, ArrayList<Card> deck2) {

        for(int i = 0; i < deck2.size(); i++) {

            if(deck2.get(i).diseaseType == card.diseaseType) {
                deck1.add(deck2.get(i));
                deck2.remove(i);
                break;
            }

        }

        //finds the non-common card in deck1 and adds it to deck2
        for(int i = 0; i < deck1.size(); i++) {
            if(deck1.get(i).diseaseType != card.diseaseType) {

                Card temp = deck1.get(i);
                deck2.add(temp);
                deck1.remove(i);
                break;

            }
        }

        return deck1;
    }

    /**
     * Draw a card from the player deck and checks if it's an epidemic card
     */
    public int drawCards(Deck deck, Deck infection_deck, Deck discard, Map board, int index) {
        Card card_drawn = deck.remove();

        if(card_drawn.isEpidemic == false) {

            cardsInHand.add(card_drawn);

        } 
        else {

            index = epidemicCard(infection_deck, discard, board, index);

        }

        return index;
    }

    /**
     * If the card is an epidemic card, increases the infection rate index by 1, infects the city of the drawn card by 3,
     * and shuffles discarded cards and places it on top of the infection deck. 
     * 
     * @param deck of infection cards
     * @param deck of discarded cards
     * @param map
     * @param current infection rate
     * @return updated infection rate index
     */
    public int epidemicCard(Deck infection_deck, Deck discarded, Map board, int index) {
        index++;        

        if(infection_deck.deck.isEmpty() == false) {

            Card flipped = infection_deck.remove();
            discarded.add(flipped); 

            String city = flipped.cityName;

            City infected = board.findCity(city);
            boolean outbreak = infected.addCube(3, "epidemic card");

            //outbreak happens
            if(outbreak == true) {
                board.outbreak(infected);
            }

        } else {
            infection_deck = discarded;
        }

        discarded.shuffle();

        for(int i = 0; i < discarded.deck.size(); i++) {
            infection_deck.add(discarded.get(i));
        }

        discarded.clear();
        return index;
    }

}
