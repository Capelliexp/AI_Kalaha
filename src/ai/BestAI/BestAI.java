package ai.BestAI;

import ai.Global;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import kalaha.*;
import server.*;
import ai.BestAI.*;

/*
Denna fil används för alla funktioner som vi vill implementera.
Vi kan även skapa globala variabler här som alla MinMaxNodes
kan nå (tror jag).
*/

public class BestAI{
	private MinMaxNode root;			//pointer to the current root. Must be changed after every move.
	public static int maxDepth = 5;		//maximum depth of the tree (var will change but real depth won't)
	private int ourMoveCount;
	private int totalMoveCount;
	
	private static BestAI AIinstance;	//only use to call methods, variables will be faulty
	private ServerGUI GUIref;
	
	// Getters ------------------------------- Getters
	
	// Setters ------------------------------- Setters
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
		int move = 1; //(1-6)
		
		/*
		 * MOVE CALC
		*/
		
		this.ourMoveCount++;
		
		return move;
	}
	
	public static BestAI GetInstance(){
        if (AIinstance == null){
        	GameState randBoard = new GameState();
        	AIinstance = new BestAI(randBoard);
        }
        return AIinstance;
    }
	
	public void PrintString(String someString){
		GUIref.addText(someString);
	}
	
	public int HandleMove(int ambo){
		this.SetChildAsRoot(ambo-1);
		this.totalMoveCount++;
		BestAI.maxDepth++; 		//to keep track of how many node levels deep we travel
		
		return 1;
	}
	
	// Constructor ------------------------------- Constructor
	public BestAI(GameState currentBoard){
		this.root = new MinMaxNode(currentBoard);
		this.ourMoveCount = 0;
		this.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		GUIref = ServerGUI.getInstance();
		
		PrintString("BestAI construction fin");
	}
	
	public BestAI(){
		GameState newBoard = new GameState();
		this.root = new MinMaxNode(newBoard);
		
		this.ourMoveCount = 0;
		this.totalMoveCount = 0;
		
		BestAI.AIinstance = this;
		GUIref = ServerGUI.getInstance();
		
		PrintString("BestAI construction fin");
	}
}



























































