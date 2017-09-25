package ai.BestAI;

import kalaha.*;
import server.*;
/*import ai.Global;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import ai.BestAI.*;*/

public class BestAI{
	private MinMaxNode root;			//pointer to the current root. Must be changed after every move.
	
	public static int playerID;
	public static int enemyID;
	public static int realDepth = 2;	//OBS! does not include the root, depth 0 is root
	public static int maxDepth = 2;		//maximum depth of the tree (var will change but realDepth won't)
	public static int minDepth = 0;
	public static int ourMoveCount;
	public static int totalMoveCount;
	
	public static int treeCounter; 
	
	private static BestAI AIinstance;	//only use to call methods, variables will be faulty
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
		this.root = root.GetChild(childNr);
		this.root.SetParent(this.root);		//disconnect the unnecessary tree from main loop, marking it as garbage
		
		return 1;
	}
	
	public int SetMaxDepth(int newMaxDepth){
		BestAI.maxDepth = newMaxDepth;
		return 1;
	}
	
	// Functions ------------------------------- Functions
	public int GetMove(GameState currentBoard){ //returns the move (1-6) to be done
		System.out.println("GetMove() start");
		//BestAI.minDepth = BestAI.totalMoveCount;
		
		BestAI.treeCounter = 0;	//temp counter
		
		System.out.println("ExtendTree() start");
		root.ExtendTree();
		System.out.println("ExtendTree() end");
		
		int move = SearchEngine.AlphaBetaSearch(root);	//returns 1-6
		
		BestAI.ourMoveCount++;
		
		System.out.println("GetMove() end");
		return move;
	}
	
	public void PrintString(String someString){
		GUIref.addText(someString);
	}
	
	public int HandleMove(int ambo){
		this.SetChildAsRoot(ambo-1);
		BestAI.totalMoveCount++;
		BestAI.minDepth = BestAI.totalMoveCount;
		BestAI.maxDepth = BestAI.realDepth + BestAI.totalMoveCount;
		
		System.out.println("NEW BestAI.maxDepth = " + BestAI.maxDepth);
		
		//System.out.println("ExtendTree() start");
		//root.ExtendTree();
		//System.out.println("ExtendTree() end");
		
		return 1;
	}
	
	// Constructor ------------------------------- Constructor
	public BestAI(GameState currentBoard){
		this.root = new MinMaxNode(currentBoard);
		
		BestAI.ourMoveCount = 0;
		BestAI.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		GUIref = ServerGUI.getInstance();
		this.SearchEngine = new MinMaxSearch();
		
		PrintString("BestAI construction fin");
	}
	
	public BestAI(){
		GameState newBoard = new GameState();
		this.root = new MinMaxNode(newBoard);
		
		BestAI.ourMoveCount = 0;
		BestAI.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		this.GUIref = ServerGUI.getInstance();
		this.SearchEngine = new MinMaxSearch();
		
		PrintString("BestAI construction fin");
	}
}



























































