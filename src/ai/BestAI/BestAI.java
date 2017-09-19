package ai.BestAI;

import kalaha.*;
import ai.BestAI.*;

/*
Denna fil används för alla funktioner som vi vill implementera.
Vi kan även skapa globala variabler här som alla MinMaxNodes
kan nå (tror jag).
*/

public class BestAI{
	private MinMaxNode root;	//pointer to the current root. Must be changed after every move.
	private static int maxDepth;		//maximum depth of the tree
	
	// Getters ------------------------------- Getters
	public static int GetMaxDepth(){
		return maxDepth;
	}
	
	// Functions ------------------------------- Functions
	public int GetMove(GameState currentBoard){ //returns the move (1-6) to be done
		return 1;
	}
	
	// Constructor ------------------------------- Constructor
	public BestAI(GameState currentBoard){
		this.root = new MinMaxNode(currentBoard);
		BestAI.maxDepth = 5;
	}
	
}