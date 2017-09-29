package ai.BestAI;

import kalaha.*;
import java.util.*;
/*import java.io.Console;
import ai.BestAI.*;*/

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int childNr;								//value of 1-6
	private boolean fertility;							//if node should create more children or not
	private int nodeDepthLevel;							//depth of the current node from the original root
	private MinMaxNode parent;							//pointer to parent
	//public MinMaxNode[] children = new MinMaxNode[6];	//array of children, OBS! temporary public - pre anton merge
    public ArrayList<MinMaxNode> listOfChildren = new ArrayList<>(); 
	private boolean invalidMove;
	public boolean gotChildren;				//bool if this node has children, OBS! temporary public
	public int childrenAmount;
	
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
	
	/*public MinMaxNode GetChild(int i){	//pre anton merge
		if(i < 0 || i > 6){	//ERROR
			System.out.println("GetChild() ERROR - INVALID_PARAM");
			return this;
		}
		
		MinMaxNode child;
		if(children[i] != null){
			child = this.children[i];
			return child;
		}
		else{
			System.out.println("GetChild() ERROR - NO_CHILD");
			return this;
		}
	}*/
	
	public MinMaxNode GetChild(int i){
        MinMaxNode child = null;
        for(int a=0;a<listOfChildren.size();a++){
            if(this.listOfChildren.get(a).childNr == i){
                System.out.println("HEJ");
                child = this.listOfChildren.get(a); 
            }
        }
        if(child == null){
            System.out.println("GetChild " + i + " error");
        }
        return child;
	}
        
	public boolean GetValid(){
		return !(this.invalidMove);
	}
	
	// Setters ------------------------------- Setters
	public int SetParent(MinMaxNode newParent){ //to change the parent of the new root to itself
		this.parent = newParent;
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
		
		//if not legal - stop fertility
		if(legal == false){
			this.fertility = false;
			this.invalidMove = true;
			returnValue = 0;
		}
		return returnValue;
	}
	
	/*private int CreateChildren(){		//pre anton merge
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth) && (this.nodeDepthLevel >= BestAI.minDepth)){
			for(int i = 0; i < 6; i++){
				children[i]  = new MinMaxNode(this, i+1);	//OBS! i+1
			}
			gotChildren = true;
		}
		return 1;
	}*/
	
	private int CreateChildren(){
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth) && (this.nodeDepthLevel >= BestAI.minDepth)){
			for(int i = 1; i <= 6; i++){
				MinMaxNode temp  = new MinMaxNode(this, i);
                if(temp.GetValid() == true){
                	this.childrenAmount++;
                	//System.out.println("CreateChildren(): " + i);
                	listOfChildren.add(temp);
                }
			}
			gotChildren = true;
		}
		return 1;
	}
	
	public boolean TerminalTest(){
            boolean endedGame = this.state.gameEnded();
            if(endedGame == true){
            	this.fertility = false;
            }
            
            return endedGame;
        }
	
	/*public int ExtendTree(){	//pre anton merge
		BestAI.treeCounter++;
		System.out.println("ExtendTree() nr " + BestAI.treeCounter + " started");
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth)){
			if(children[0] != null){		//does this work ?!?
				for(int i = 0; i < 6; i++){
					children[i].ExtendTree();
				}
			}
			else{
				this.CreateChildren();
			}
		}
		
		return 1;
	}*/
	
	public int ExtendTree(){
		BestAI.treeCounter++;
		//System.out.println("ExtendTree() nr " + BestAI.treeCounter + " started");
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth)){
			if(listOfChildren.isEmpty() != true){		//does this work ?!?
				for(int i = 0; i < listOfChildren.size(); i++){
					listOfChildren.get(i).ExtendTree();
				}
			}
			else{
				this.CreateChildren();
			}
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
		this.invalidMove = false;
		this.gotChildren = false;
		this.childrenAmount = 0;
		
		Action(childNr);
		CreateChildren();
	}
	
	public MinMaxNode(GameState trueState){	//constructor when created as root. OBS! �ndra inte trueState
		this.parent = this;		//OBS! High risk
		this.childNr = -1;		//not a child
		this.fertility = true;
		this.nodeDepthLevel = 0;
		this.state = trueState;	//this node represents the current game (DONT MAKE CHANGES)
		this.invalidMove = false;
		this.gotChildren = false;
		this.childrenAmount = 0;
		
		CreateChildren();
	}
}


























































