package ai;

import java.util.*;
import java.util.LinkedList;
import ai.AIClient;
import ai.

public class MinMaxNode {
	private GameState state;
	private int value;
	private MinMaxNode parent;
	private List<MinMaxNode> children = new LinkedList<MinMaxNode>();
	
	public MinMaxNode(MinMaxNode parent){
		state = new GameState();
		state = parent.clone();	//fel
		
		for(int i = 0; i > 6; i++){
			children[i] = new MinMaxNode(this); //fel
		}
	}
	
	public GameState GetState(){
		return this.state;
	}
	
	public boolean Action(int move){
		boolean legal = state.makeMove(move);
		return legal;
	}
}