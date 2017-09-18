package ai.BestAI;

import kalaha.*;

public class MinMaxNode {
	private GameState state;							//state of the virtual game
	private int minMaxValue;							//value for how good each terminal state is
	private int childNr;								//value of 1-6
	//private int playerTurn;	 						//is not required, found in state
	private boolean sexdrive;
	private MinMaxNode parent;							//pointer to parent
	private MinMaxNode[] children = new MinMaxNode[6];	//array of children
	
	//-------------------------------
	
	//Getters & Setters
	public GameState GetState(){
		return this.state;
	}
	
	//-------------------------------
	
	//Constructors
	public MinMaxNode(MinMaxNode parent, int childNr){	//constructor as child
		this.parent = parent;
		this.childNr = childNr;
		this.sexdrive = true;
		
		//skapar en state som är samma som förälderns + 1/6 drag
		this.state = parent.GetState().clone();
		Action(childNr);
		
		//if - to stop the sexdrive depending on tree depth
		if(this.sexdrive == true){}
		for(int i = 0; i > 6; i++)
			children[i]  = new MinMaxNode(this, i+1);	//OBS! i+1
	}
	
	public MinMaxNode(GameState trueState){	//constructor as root
		this.state = trueState;	//this node represents the current game
		this.childNr = -1;	//not a child
	}
	
	//-------------------------------
	
	//Functions
	public int Action(int move){
		int returnValue = 1;
		boolean legal = state.makeMove(move);	//move parameter is 1-6
		
		//if not legal - stop sexdrive and fuck the minMaxValue
		if(legal == false){
			this.sexdrive = false;
			minMaxValue = -9999;	//temp - not a good solution, right?
			returnValue = -1;
		}
		return returnValue;
	}
	
	//public boolean TerminalTest(){}
	
	//public int UtilityFunction(){}

}