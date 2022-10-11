------------------------------------------------------------------------

PROJECT TITLE: Pandemic - Board Game

PURPOSE OF PROJECT:
This project is a recreation of the board game called Pandemic (https://www.zmangames.com/en/products/pandemic/) with a few modifications. Instead of having players, there are 5 pawns that move across the map playing the game with simple algorithms that define their behaviors. The map can be configured to either the actual board of the game or randomly generated graphs ranging from sparse to dense. Also, at the end of each game, the program logs the state of the game in output.txt file which is later used to analyze the behaviors and outcomes or the game. 


VERSION or DATE: 05/27/2021

HOW TO START THIS PROJECT: 
The main method is in the Main class. The user can change the seed values and complexity of the graph to any numbers they want, but the complexity should be less than 50. 

AUTHORS: Oyu Gantumur

USER INSTRUCTIONS:
The program can be run with either the config file or the gameboard file. The gameboard file contains predefined map information where cities have differing numbers of neighbors. On the other hand, the user can manipulate the config file in any way she wants and specify how many neighbors each city can be connected to. In order to change the information in the config file, the user should enter one city (with no space) followed by its disease type in one line. The user can also change the seed values and the complexity of the graph. 