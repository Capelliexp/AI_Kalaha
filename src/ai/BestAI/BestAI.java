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
	private MinMaxNode root;	//pointer to the current root. Must be changed after every move.
	public static int maxDepth;		//maximum depth of the tree
	
	ServerGUI GUIref;
	
	// Getters ------------------------------- Getters
	public static int GetMaxDepth(){
		return maxDepth;
	}
	
	// Setters ------------------------------- Setters
	public int SetMaxDepth(int newMaxDepth){
		BestAI.maxDepth = newMaxDepth;
		
		return 1;
	}
	
	// Functions ------------------------------- Functions
	public int GetMove(GameState currentBoard){ //returns the move (1-6) to be done
		return 1;
	}
	
	// Constructor ------------------------------- Constructor
	public BestAI(GameState currentBoard){
		GUIref = ServerGUI.getInstance();
		this.root = new MinMaxNode(currentBoard);
		BestAI.maxDepth = 5;
		
		PrintString("BestAI construction fin");
	}
	
	public void PrintString(String someString){
		GUIref.addText(someString);
	}
	
}