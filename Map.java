import java.util.*;
import java.util.Scanner;
import java.io.*;

/**
 * Creates a map and a deck of cards
 *
 * @author Oyu Gantumur
 */
public class Map
{
    LinkedList<String> cities;
    LinkedList<String> diseases;
    LinkedList<City> boardCities;
    Random random;
    Deck deck;
    Deck deckPlayer;    
    int n;
    int numResStation = 0;
    
    boolean outbreak;
    int outbreak_marker;
    String gameStatus = "";
    
    Disease black = new Disease("black");
    Disease blue = new Disease("blue");
    Disease yellow = new Disease("yellow");
    Disease red = new Disease("red");
        
    HashMap<City, LinkedList<City>> map = new HashMap<City, LinkedList<City>>();

    /**
     * Constructor for objects of class Map
     */
    public Map(Reader reader, String filename, int n, Random random)
    {        
        this.random = random;
        reader = new Reader(filename);
        this.cities = reader.cities;
        this.diseases = reader.diseases;
        this.n = n;
        
        this.outbreak_marker = 1;
        this.outbreak = false;
        
        this.boardCities = new LinkedList<City>();
        this.deck = new Deck(random);
        this.deckPlayer = new Deck(random);
    }
    
    /**
     * Creates a general deck of cards using the information in the file and 
     * assigns disease types for board game cities
     */
    public Deck createDeck() {
        
        if(n != 0) {
        
            for(int i = 0; i < cities.size(); i++) {
                
                Card card = new Card(findDisease(diseases.get(i)), cities.get(i));
                deck.add(card);
                
            }
        } 
        else {
            
            for(int i = 0; i < boardCities.size()/4; i++) {
                Card card = new Card(black, boardCities.get(i).name);
                deck.add(card);
                boardCities.get(i).setDisease(black);
            }
            
            for(int i = boardCities.size()/4; i < boardCities.size()/2; i++) {
                Card card = new Card(blue, boardCities.get(i).name);
                deck.add(card);
                boardCities.get(i).setDisease(blue);
            }
            
            for(int i = boardCities.size()/2; i < 3*boardCities.size()/4; i++) {
                Card card = new Card(yellow, boardCities.get(i).name);
                deck.add(card);
                boardCities.get(i).setDisease(yellow);
            }
            
            for(int i = 3*boardCities.size()/4; i < boardCities.size(); i++) {
                Card card = new Card(red, boardCities.get(i).name);
                deck.add(card);
                boardCities.get(i).setDisease(red);
            }
        }
        
        deck.shuffle();
        return deck;
    }
    
    /**
     * Creates and shuffles a deck of player cards with 4 epidemic cards
     * 
     * @return a shuffled deck of player cards
     */
    public Deck createPlayerDeck() {
        Deck gen = deck;

        for(int i = 0; i < gen.deck.size(); i++) {
            deckPlayer.add(gen.get(i));
        }

        for(int i = 0; i < 4; i++) {
            deckPlayer.add(new Card(true));
        }
        System.out.println("---------");
        
        deckPlayer.shuffle();        
        return deckPlayer;
    }    
        
    /**
     * Returns the disease with the provided name
     * 
     * @param the name of the disease to be search for
     */
    public Disease findDisease(String name) {
        
        if(name.equals("black")) {
            return black;
        }
        else if(name.equals("blue")) {
            return blue;
        }
        else if(name.equals("yellow")) {
            return yellow;
        }
        else if (name.equals("red")) {
            return red;
        }
        else {
            System.out.println("The disease type does not exist.");
            return null;
        }
        
    }
    
    /**
     * Converts the list of city names to a City object
     * 
     * @return a list of Cities
     */
    public LinkedList<City> createCities() {
        LinkedList<City> locations = new LinkedList<City>();
        
        for(int i = 0; i < cities.size(); i++) {
            
            Disease diseaseType = findDisease(diseases.get(i));
            City city = new City(cities.get(i), diseaseType);
            
            locations.add(city);
            
        }
        
        return locations;
    }
    
    /**
     * Creates a map with randomly chosen vertices.
     * Picks a city in order one by one, randomly selects n other cities from the list,
     * makes them neighbors with the initially picked city. 
     * 
     * @param n number of neighbors each city is connected to. 
     */
    public void createMap(int n) {
        LinkedList<City> vertices = createCities();
        LinkedList<City>[] adjList = new LinkedList[vertices.size()];
        
        for(int i = 0; i < vertices.size(); i++) {
            City vertex = vertices.get(i);
            
            //connect each city with n other randomly chosen cities
            for(int k = 0; k < n; k++) {                
                int pos = random.nextInt(vertices.size());
                
                if(pos == i) {
                    pos = random.nextInt(vertices.size());
                }
                
                //makes the connection two-way: from the city to neighbor
                if(adjList[i] == null) {
                    
                    adjList[i] = new LinkedList<City>();
                    adjList[i].add(vertices.get(pos));
                    
                }
                else if (adjList[i].contains(vertices.get(pos)) == false) {
                    adjList[i].add(vertices.get(pos));
                }                                
                
                //from the neighbor to the city
                if(adjList[pos] == null) {
                    
                    adjList[pos] = new LinkedList<City>();
                    adjList[pos].add(vertex);
                    
                }                
                else if(adjList[pos].contains(vertex) == false) {
                    adjList[pos].add(vertex);
                }
                
                map.put(vertex, adjList[i]);               
            }           
        }
        
    }  
    
    /**
     * Creates the game board provided in the file
     */
    public void createMap() {       
        
        try{
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("gameboard.txt")));
            Scanner lineScan = null;
            String line = null;
            
            while(scanner.hasNextLine()) {
                LinkedList<City> adjList = new LinkedList<City>();
                City vertex = null;                
                String city = "";
                String neighbor = "";
                String word = "";
                               
                line = scanner.nextLine();
                lineScan = new Scanner(line);
                city = lineScan.next();
                
                //scans and creates each city as a vertex
                while(word.contains(":") == false) { 
                    
                    word = lineScan.next();
                     
                    if(word.contains(":") == false) {
                        city = city + " " + word;
                    }
                }                 
                
                if(containsCity(boardCities, city) == null) {
                    
                    vertex = new City(city);
                    boardCities.add(vertex);
                    
                }
                
                //adds the neighboring countries to its LinkedList and puts it on the map
                while(lineScan.hasNext()) {                  
                    word = lineScan.next();                     
                    
                    if(word.contains(",") == true) {
                        
                        neighbor += word.substring(0, word.length()-1);
                        
                        if(containsCity(boardCities, neighbor) == null) {
                            
                            City new_neighbor = new City(neighbor);
                            boardCities.add(new_neighbor);
                            adjList.add(boardCities.getLast());
                            
                        }
                        else {
                            adjList.add(containsCity(boardCities, neighbor));
                        }
                        
                        neighbor = "";
                        
                    }
                    else if (word.contains(",") == false) {
                        
                        //the word is the end of the line
                        if(lineScan.hasNext() == false) {  
                            
                            if(neighbor.equals("") == false) {
                                neighbor += word; 
                            } 
                            else {
                                neighbor = word;
                            }
                            
                            if(containsCity(boardCities, neighbor) == null) {
                                
                                City new_neighbor = new City(neighbor);
                                boardCities.add(new_neighbor);
                                adjList.add(boardCities.getLast());
                                
                            }
                            else {
                                adjList.add(containsCity(boardCities, neighbor));
                            }
                            
                        }
                        else {
                            neighbor += word + " ";
                        }
                        
                    }                                                          
                }
                
                map.put(containsCity(boardCities, city), adjList);
            }
            
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
         
    }
    
    /**
     * Finds a city with the same name as the provided string in the given list
     * 
     * @param list of cities to search in
     * @param String city to be searched for
     * @return City with the same name
     */
    public City containsCity(LinkedList<City> list, String city) {
        
        for(int i = 0; i < list.size(); i++) {
            
            if(list.get(i).name.equals(city)) {
                return list.get(i);
            }
            
        }
        
        return null;
    }
    
    /**
     * Finds a city with the same name as the provided string on the map
     * 
     * @param String city to be search for
     * @return City with the same name
     */
    public City findCity(String city) {
        
        for(City curr: map.keySet()) {
            
            if(curr.name.equals(city)) {
                return curr;
            }                       
        }
        
        return null;
    }
    
    /**
     * Draws a card from the infection deck and adds 1 cube to the city
     * 
     * @param infect_rate the number of cards to draw
     * @param deck of infection cards
     * @param deck of discarded cards
     */
    public void infect(int infect_rate, Deck cards, Deck discard) {  
        
        for(int i = 0; i < infect_rate; i++) {
            
            if(cards.deck.isEmpty() == false) {
                Card flipped = cards.remove();
                discard.add(flipped);
                
                String city = flipped.cityName;

                outbreak = findCity(city).addCube(1, "infection");
                
                //outbreak happens in the city
                if(outbreak == true) {
                    outbreak(findCity(city));
                }
                
            }           
            else {   //if the deck is empty, replaces it with discarded cards
                cards = discard;
                System.out.println("Infection deck is run out");
            }
        }
    }
    
    /**
     * Adds 1 cube to the cities connected to outbreak-happened city
     * 
     * @param the city where outbreak happened
     */
    public void outbreak(City city) {
        outbreak_marker++;
        city.setOutbreak(true);
        LinkedList<City> neighbors = map.get(city);
        
        for(int i = 0; i < neighbors.size(); i++) {
            
            City connected = neighbors.get(i);
            
            if(connected.outbreak == false) {
                boolean sub_outbreak = connected.addCube(1, "outbreak");
                
                if(sub_outbreak == true) {
                    //outbreak(connected);
                }
            }
                    
        }
        
        city.outbreak = false;
        System.out.println("Outbreak marker is at " + outbreak_marker);
    }
    
    /**
     * Updates the gameOver status
     * 
     * @param deck of player cards
     * @return true if game is over
     */
    public boolean updateStatus(Deck deck) {
        int limit = 20;
        
        if(outbreak_marker >= 7) {
            gameStatus = "Pawns lost the game! Outbreak marker is at " + outbreak_marker;
            System.out.println(gameStatus);
            return true;
        }
        else if (black.count >= limit || blue.count >= limit || yellow.count >= limit || red.count >= limit) {
            gameStatus = "Pawns lost the game! Run out of disease cubes.";
            System.out.println(gameStatus);
            return true;
        }
        else if (deck.deck.isEmpty() == true) {
            gameStatus = "Pawns lost the game! Players deck is finished";
            System.out.println(gameStatus);
            return true;
        }
        else if (black.cured == true && blue.cured == true && yellow.cured == true && red.cured == true) {
            gameStatus = "Pawns won the game!!! Cured all diseases.";
            System.out.println(gameStatus);
            return true;
        }
        
        return false;
    }
        
    /**
     * Converts the map into a string
     */
    public String toString() {
        String ret = "";
        
        for(City curr: map.keySet()) {
            String key = curr.name;
            String neighbors = map.get(curr).toString();
            ret = ret + key + ": " + neighbors + "\n";
        }
        
        return ret;
    }
    
    public String toStringOutput() {
        String ret = "The number of neighbors each city has: ";
        
        if(n == 0) {
            ret += "Default board game\n";
        } 
        else {
            ret += n + "\n";
        }
        
        ret += "Game status: " + gameStatus + "\n";
        ret += black.toString() + "\n";
        ret += blue.toString() + "\n";
        ret += yellow.toString() + "\n";
        ret += red.toString() + "\n";
        
        ret += "The number of research stations built: " + numResStation + "\n";
        ret += "The outbreak marker: " + outbreak_marker + "\n";        
        
        return ret;
    }
}





