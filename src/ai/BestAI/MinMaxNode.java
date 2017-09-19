package ai.BestAI;

import kalaha.*;
import ai.BestAI.*;

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int utilityValue;							//value for how good each terminal state is
	private int childNr;								//value of 1-6
	private boolean fertility;							//if node should create more children or not
	private int nodeDepthLevel;							//depth of the current node from the original root
	private MinMaxNode parent;							//pointer to parent
	private MinMaxNode[] children = new MinMaxNode[6];	//array of children
	
	// Getters ------------------------------- Getters
	public GameState GetState(){
		return this.state;
	}
	
	public int GetUtilityValue(){
		return this.utilityValue;
	}
	
	public int GetChildNr(){
		return this.childNr;
	}

	public int GetNodeDepthLevel(){
		return this.nodeDepthLevel;
	}
	
	public MinMaxNode GetParent(){
		return this.parent;
	}
	
	// Setters ------------------------------- Setters
	public int SetParent(MinMaxNode newParent){ //to change the parent of the new root to itself
		this.parent = newParent;
		return 1;
	}
	
	public int SetUtilityValue(int newUtilityValue){
		this.utilityValue = newUtilityValue;
		return 1;
	}
	
	public int SetChildNr(int newChildNr){
		this.childNr = newChildNr;
		return 1;
	}
	
	// Functions ------------------------------- Functions
	private int Action(int move){
		int returnValue = 1;
		boolean legal = this.state.makeMove(move);	//move parameter is 1-6
		
		//if not legal - stop fertility and change the utilityValue
		if(legal == false){
			this.fertility = false;
			utilityValue = -9999;	//temp - not a good solution, right?
			returnValue = -1;
		}
		return returnValue;
	}
	
	private int CreateChildren(){
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.GetMaxDepth()))
			for(int i = 0; i > 6; i++)
				children[i]  = new MinMaxNode(this, i+1);	//OBS! i+1
		
		return 1;
	}
	
	//public boolean TerminalTest(){}
	
	//public int UtilityFunction(){}
	
	// Constructors ------------------------------- Constructors
	public MinMaxNode(MinMaxNode parent, int childNr){	//constructor when created as child
		this.parent = parent;
		this.childNr = childNr;
		this.fertility = true;
		this.nodeDepthLevel = parent.GetNodeDepthLevel() + 1;
		this.state = parent.GetState().clone();
		
		Action(childNr);
		CreateChildren();
	}
	
	public MinMaxNode(GameState trueState){	//constructor when created as root. OBS! ändra inte trueState
		this.parent = this;		//OBS! High risk
		this.childNr = -1;		//not a child
		this.fertility = true;
		this.nodeDepthLevel = 0;
		this.state = trueState;	//this node represents the current game
		
		CreateChildren();
	}
	
	// Destructors ------------------------------- Destructors
	
	/* WTF Java har inga destruktorer... Så vi vet inte säkert om minnet kommer frigöras när
	   vi raderar en nod... Om vi helt enkelt inte har några pekare till ett objekt längre
	   (vilket hade gett minnesläckor i C++) så kommer Java att radera objektet förr eller
	   senare */
	
}


























