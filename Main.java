import java.util.*;

/**
 * Runs the program
 *
 * @author Oyu Gantumur
 * @version 05/13/2021
 */
public class Main
{

    public static void main(String[] args) {
        
        Output output = new Output("output");

        int[] seeds = {117, 317, 527, 712, 823};
        int[] complexity = {0, 2, 5, 8, 11};

        for(int seed_value : seeds) {
            
            Random random = new Random(seed_value);
            Reader reader = new Reader("config.txt");

            for(int n : complexity) {
                
                int[] infection_rate = new int[]{2, 2, 2, 3, 3, 4, 4};
                int rate_index = 0;
                Map board = new Map(reader, "config.txt", n, random);

                if(n == 0) {
                    board.createMap();
                }
                else {
                    board.createMap(n);
                }

                Deck deck = board.createDeck();  
                Deck playerCards = board.createPlayerDeck();
                Deck infectionCards = deck;
                Deck discardedCards = new Deck(random);

                Pawn pawn1 = new Pawn(board, board.map.keySet().iterator().next(), random);
                Pawn pawn2 = new Pawn(board, board.map.keySet().iterator().next(), random);
                Pawn pawn3 = new Pawn(board, board.map.keySet().iterator().next(), random);
                Pawn pawn4 = new Pawn(board, board.map.keySet().iterator().next(), random);
                Pawn pawn5 = new Pawn(board, board.map.keySet().iterator().next(), random);

                Pawn[] pawns = new Pawn[]{pawn1, pawn2, pawn4};

                boolean gameOver = false;

                //game set up
                for(int i = 0; i < 9; i++) {
                    Card flipped = infectionCards.remove();
                    discardedCards.add(flipped);

                    String city = flipped.cityName;

                    if(i < 3) {
                        board.findCity(city).addCube(3, "setup");
                    }
                    else if (i >= 3 && i < 6) {
                        board.findCity(city).addCube(2, "setup");
                    }
                    else {
                        board.findCity(city).addCube(1, "setup");
                    }

                }

                while(gameOver == false) {
                    //System.out.println("PlayerCards: " + playerCards.toString());
                    //System.out.println("InfectionCards: " + infectionCards.toString());

                    pawn1.behave(1, 1);
                    rate_index = pawn1.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    rate_index = pawn1.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    System.out.println("Infection rate: " + infection_rate[rate_index]);
                    board.infect(infection_rate[rate_index], infectionCards, discardedCards);

                    gameOver = board.updateStatus(playerCards);
                    if(gameOver == true) {
                        break;
                    }

                    pawn2.behave(2, 2);
                    rate_index = pawn2.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    rate_index = pawn2.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    System.out.println("Infection rate: " + infection_rate[rate_index]);
                    board.infect(infection_rate[rate_index], infectionCards, discardedCards);

                    gameOver = board.updateStatus(playerCards);            
                    if(gameOver == true) {
                        break;
                    }

                    pawn3.behave(3, 3, pawns);
                    rate_index = pawn3.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    rate_index = pawn3.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    System.out.println("Infection rate: " + infection_rate[rate_index]);
                    board.infect(infection_rate[rate_index], infectionCards, discardedCards);

                    gameOver = board.updateStatus(playerCards);
                    if(gameOver == true) {
                        break;
                    }

                    pawn4.behave(4, 1);
                    rate_index = pawn4.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    rate_index = pawn4.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    System.out.println("Infection rate: " + infection_rate[rate_index]);
                    board.infect(infection_rate[rate_index], infectionCards, discardedCards);

                    gameOver = board.updateStatus(playerCards);
                    if(gameOver == true) {
                        break;
                    }

                    pawn5.behave(5, 3, pawns);
                    rate_index = pawn5.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    rate_index = pawn5.drawCards(playerCards, infectionCards, discardedCards, board, rate_index);
                    System.out.println("Infection rate: " + infection_rate[rate_index]);
                    board.infect(infection_rate[rate_index], infectionCards, discardedCards);

                    gameOver = board.updateStatus(playerCards);

                }

                output.write("The seed value: " + seed_value + "\n");
                output.write(board.toStringOutput());
                output.write("Infection rate is at " + infection_rate[rate_index]);
                output.write("\n-------------------------\n");

            }
        }
        
        output.close();
    }      

}
