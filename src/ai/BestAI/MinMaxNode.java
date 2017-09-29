package ai.BestAI;

import kalaha.*;
import java.util.*;

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int childNr;								//value of 1-6
	private boolean fertility;							//if node can create more children
	private int nodeDepthLevel;							//depth of the current node from the original root
	private MinMaxNode parent;							//pointer to parent
	private boolean validMoveBool;
	
    public ArrayList<MinMaxNode> listOfChildren = new ArrayList<>();	//array of children
	
	// Getters ------------------------------- Getters
	public GameState GetState(){
		return this.state;
	}

	public int GetChildNr(){
		return this.childNr;
	}

	public boolean GetFertility(){
		return this.fertility;
	}
	
	public int GetNodeDepthLevel(){
		return this.nodeDepthLevel;
	}
	
	public MinMaxNode GetParent(){
		return this.parent;
	}
        
	public boolean GetValid(){
		return this.validMoveBool;
	}
	
	// Setters ------------------------------- Setters
	public int SetParent(MinMaxNode newParent){ //to change the parent of the new root to itself
		this.parent = newParent;
		return 1;
	}
	
	// Functions ------------------------------- Functions
	private int Action(int move){
		boolean legal = this.state.makeMove(move);	//move parameter is 1-6
		
		if(legal == false){
			this.fertility = false;
			this.validMoveBool = false;
		}
		return 1;
	}
	
	private int CreateChildren(){
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth) && (this.nodeDepthLevel >= BestAI.minDepth)){
			for(int i = 1; i <= 6; i++){
				MinMaxNode temp  = new MinMaxNode(this, i);
                if(temp.GetValid() == true){
                	listOfChildren.add(temp);
                }
			}
		}
		return 1;
	}
	
	public int ExtendTree(){
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth)){
			if(listOfChildren.isEmpty() != true)
				for(int i = 0; i < listOfChildren.size(); i++)
					listOfChildren.get(i).ExtendTree();
			else
				this.CreateChildren();
		}
		return 1;
	}
	
	// Constructors ------------------------------- Constructors
	public MinMaxNode(MinMaxNode parent, int childNr){	//constructor when created as child
		this.parent = parent;
		this.childNr = childNr;
		this.fertility = true;
		this.nodeDepthLevel = parent.GetNodeDepthLevel() + 1;
		this.state = parent.GetState().clone();
		this.validMoveBool = true;
		
		Action(childNr);
		CreateChildren();
	}
	
	public MinMaxNode(GameState trueState){	//constructor when created as root. OBS! �ndra inte trueState
		this.parent = this;
		this.childNr = -1;
		this.fertility = true;
		this.nodeDepthLevel = 0;
		this.state = trueState;		//this node represents the current game (DONT MAKE CHANGES)
		this.validMoveBool = true;
		
		CreateChildren();
	}
}


























































