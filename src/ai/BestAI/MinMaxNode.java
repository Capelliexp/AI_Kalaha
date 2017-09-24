package ai.BestAI;

import kalaha.*;
import ai.BestAI.*;

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int childNr;								//value of 1-6
	private boolean fertility;							//if node should create more children or not
	private int nodeDepthLevel;							//depth of the current node from the original root
	private MinMaxNode parent;							//pointer to parent
	private MinMaxNode[] children = new MinMaxNode[6];	//array of children
	
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
	
	public MinMaxNode GetChild(int i){
		return this.children[i];
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
			returnValue = -1;
		}
		return returnValue;
	}
	
	private int CreateChildren(){
		if((this.fertility == true) && (this.nodeDepthLevel < BestAI.maxDepth) && (this.nodeDepthLevel >= BestAI.minDepth))
			for(int i = 0; i < 6; i++){
				children[i]  = new MinMaxNode(this, i+1);	//OBS! i+1
			}
		return 1;
	}
	
	public boolean TerminalTest(){
            int check = 0;
            int player = state.getNextPlayer();
            if (player == 2)
                    player = 1;
            else
                player = 2;
                
            for (int i = 1; i < 7; i++) {
                check += state.getSeeds(i, player);
            }
            if (check == 0)
                return true;
            else
                return false;
        }
	
	/*public int ExtendTree(){
		System.out.println("now in ExtendTree()");
		if((this.fertility == true) && (this.nodeDepthLevel >= BestAI.minDepth) && (this.nodeDepthLevel < BestAI.maxDepth)){
			System.out.println("ExtendTree() first if true");
			if((this.nodeDepthLevel >= (BestAI.maxDepth - BestAI.totalMoveCount))){
				System.out.println("ExtendTree() second if-else true");
				this.CreateChildren();
			}
			else{
				System.out.println("ExtendTree() second if-else false");
				for(int i = 0; i < 6; i++){
					System.out.println("ExtendTree() second if-else false - i = " + i);
					children[i].ExtendTree();
				}
			}
		}
		
		return 1;
	}*/

	public int ExtendTree(){
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
	}
	
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
		this.state = trueState;	//this node represents the current game (DONT MAKE CHANGES)
		
		CreateChildren();
	}
}

























































