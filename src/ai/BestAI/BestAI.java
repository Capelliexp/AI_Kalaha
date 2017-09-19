package ai.BestAI;

import kalaha.*;
import ai.BestAI.*;

/*
Denna fil anv�nds f�r alla funktioner som vi vill implementera.
Vi kan �ven skapa globala variabler h�r som alla MinMaxNodes
kan n� (tror jag).
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