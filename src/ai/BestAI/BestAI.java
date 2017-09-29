package ai.BestAI;

import kalaha.*;
import server.*;

public class BestAI{
	private MinMaxNode root;			//pointer to the current root. Must be changed after every move.
	
	public static int playerID;
	public static int enemyID;
	public static int realDepth = 5;	//OBS! does not include the root, depth 0 is root
	public static int maxDepth = 5;		//maximum depth of the tree (var will change but realDepth won't)
	public static int minDepth = 0;		//will increase with 1 every move
	public static int totalMoveCount;	//will increase with 1 every move
	
	private static BestAI AIinstance;
	private ServerGUI GUIref;
	private MinMaxSearch SearchEngine;
	
	// Getters ------------------------------- Getters
	public static BestAI GetInstance(){
        if (AIinstance == null){
        	GameState randBoard = new GameState();
        	AIinstance = new BestAI(randBoard);
        }
        return AIinstance;
    }
	
	// Setters ------------------------------- Setters
	public int SetPlayerID(int newID){
		BestAI.playerID = newID;
		
		if(newID == 1)
			BestAI.enemyID = 2; 
		else if(newID == 2)
			BestAI.enemyID = 1;
		else
			return -1;
		
		return 1;
	}
	
	public int SetChildAsRoot(int childNr){
		for(int i = 0; i < root.listOfChildren.size(); i++)
			if(root.listOfChildren.get(i).GetChildNr() == childNr){
				this.root = root.listOfChildren.get(i);
				this.root.SetParent(this.root);		//disconnect the unnecessary tree from main loop, marking it as garbage
				return 1;
			}
		
		return 0;
	}
		
	// Functions ------------------------------- Functions
	public int GetMove(GameState currentBoard){ //returns the move (1-6) to be done
		return SearchEngine.AlphaBetaSearch(root);	//returns 1-6
	}
	
	public void PrintString(String someString){
		GUIref.addText(someString);
	}
	
	public int HandleMove(int ambo){
		this.SetChildAsRoot(ambo);
		BestAI.totalMoveCount++;
		BestAI.minDepth = BestAI.totalMoveCount;
		BestAI.maxDepth = BestAI.realDepth + BestAI.totalMoveCount;

		root.ExtendTree();
		
		return 1;
	}
	
	// Constructor ------------------------------- Constructor
	public BestAI(GameState currentBoard){
		this.root = new MinMaxNode(currentBoard);
		
		BestAI.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		GUIref = ServerGUI.getInstance();
		this.SearchEngine = new MinMaxSearch();
	}
	
	public BestAI(){
		GameState newBoard = new GameState();
		this.root = new MinMaxNode(newBoard);
		
		BestAI.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		this.GUIref = ServerGUI.getInstance();
		this.SearchEngine = new MinMaxSearch();
	}
}



























































