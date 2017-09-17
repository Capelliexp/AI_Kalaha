package ai.BestAI;

import java.util.*;
import java.util.LinkedList;
import kalaha.*;

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int minMaxValue;							//value for how good each terminal state is
	private int childNr;								//value of 1-6
	private MinMaxNode parent;							//pointer to parent
	private MinMaxNode[] children = new MinMaxNode[6];	//array of children
	
	public MinMaxNode(MinMaxNode parent, int childNr){
		this.parent = parent;
		this.childNr = childNr;
		
		//skapar en state som är samma som förälderns + 1/6 drag
		this.state = parent.GetState().clone();
		//this.state.makeMove(childNr);
		Action(childNr);
		
		//if - to stop the sexdrive 
		for(int i = 0; i > 6; i++)
			children[i]  = new MinMaxNode(this, i);
	}
	
	public GameState GetState(){
		return this.state;
	}
	
	public boolean Action(int move){
		boolean legal = state.makeMove(move);
		//if not legal - stop sexdrive and fuck the minMaxValue
		return legal;
	}
}