package ai.BestAI;

import kalaha.*;

//Ideas:
//handle multiple turns as same player, is this needed or makeMove does this?
//v might be handled wrong
//state.clone or not?
//Utility is wrong!?
//Can't find legit moves. (see one that exists)

public class MinMaxSearch {
    //private GameState current;
	private int currentDepth; //to count current depth
    private int maxDepth; //limit
    private int v;
    private int counter; //Felsökning
    
    private BestAI BestAIref;
    
    public MinMaxSearch(){
        //current = start;
        currentDepth = 0;
        maxDepth = 6; //1296; //(depth 4 minus GameOver leafs?)
        v = 0;
        counter = 1;
        
        this.BestAIref = BestAI.GetInstance();
    }
    
    public int AlphaBetaSearch(GameState state){
        int action = 1;
        int[] vA = new int[6];
        for(int i=0;i<6;i++){
            vA[i] = MaxValue(Result(state.clone(),i+1), -999999, 999999);
            currentDepth = 0;
            System.out.print("\nAlt " + (i+1) + ":" + vA[i]);
        }
        int temp = -99999;
        for(int i=0;i<6;i++){
            if(vA[i] > temp && state.moveIsPossible(i+1)){
               temp = vA[i]; 
               action = i+1;
            }
        }
        System.out.print("\nCurrent move:" + action + "::" + counter + "\n");
        System.out.print("\n----------");
        //v = MaxValue(state.clone());
        return action;
    }
    
    public int MaxValue(GameState state, int a, int b){
        //int v = 0;
        if(state.gameEnded() || (currentDepth==(6^maxDepth))){ //terminal state check
            currentDepth = 0;
            return Utility(state.clone());
        }
        
        if(state.getNextPlayer() == 2){
           v = 9999;  
       }else{
           v = -9999;
       }
        
        for(int action = 1; action <= 6; action++){ //1-6
            if(action == 1){
                currentDepth = currentDepth + 1;
            }
            if(state.getNextPlayer() == 2){
                v = Math.min(v, MaxValue(Result(state.clone(),action),a,b));
            } else{
                v = Math.max(v, MinValue(Result(state.clone(), action),a,b));  
            } 
            if(v >= b){
                return v;
            }
            a = Math.max(a, v);
        }
	return v;
    }

    public int MinValue(GameState state, int a, int b){
        //int v = 0;
        if(state.gameEnded() || (currentDepth==(6^maxDepth))){ //terminal state check
           currentDepth = 0;
           return Utility(state.clone());
       }
        
       if(state.getNextPlayer() == 1){
           v = 9999;  
       }else{
           v = -9999;
       }
       
       for(int action = 1; action <= 6; action++){ //1-6
           if(action == 1){
               currentDepth = currentDepth + 1;
            }
           if(state.getNextPlayer() == 1){
               v = Math.max(v, MinValue(Result(state.clone(), action),a,b));  
           }else{
               v = Math.min(v, MaxValue(Result(state.clone(),action),a,b));
           }
           if(v <= a){
                return v;
            }
            b = Math.min(b, v);
       }
	return v;
    }
    
    public GameState Result(GameState state, int action){
        state.makeMove(action);
        return state;
    }
    
    public int Utility(GameState state){
        counter++;
        return state.getScore(2)-state.getScore(1); //FIX THIS :I
    }
}



























































