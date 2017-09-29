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
    private int v;
    private int counter; //Felsökning
    //private int maxCounter;
    //private int minCounter;
    
    private BestAI BestAIref;
    
    public MinMaxSearch(){
        v = 0;
        counter = 1;
        this.BestAIref = BestAI.GetInstance();
    }
    
    public int AlphaBetaSearch(MinMaxNode root){
    	System.out.println("ALPHABETASEARCH START");
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!");
    	
        int action = -1;
        int[] vA = new int[6];
        for(int i = 0; i < 6; i++)
        	vA[i] = -9999;
        
        for(int i=0;i<root.listOfChildren.size();i++){
        	System.out.println("Node children amount (acc. size): " + root.listOfChildren.size());
        	System.out.println("Node children amount (acc. amou): " + root.childrenAmount);
            //maxCounter = 0;
            //minCounter = 0;
            System.out.println("---------------------");
            System.out.println("AlphaBetaSearch root child " + i);
            vA[i] = MaxValue(root.listOfChildren.get(i));
            System.out.println("Alt " + root.listOfChildren.get(i).GetChildNr() + ":" + vA[i]);
        }
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("AlphaBetaSearch post root child for");
        
        int temp = -99999;
        int chosenChildIndex = 0;
        counter = 0;
        System.out.println("Nr of children:" + root.listOfChildren.size());
        for(int i=0;i<root.listOfChildren.size();i++){
            //System.out.println("TESTING:" + root.listOfChildren.get(i).GetChildNr());
            counter++;
            if(vA[i] > temp && root.listOfChildren.get(i).GetValid()){
               temp = vA[i];
               chosenChildIndex = i;
               action = root.listOfChildren.get(i).GetChildNr();
            }
        }
        System.out.print("\nCurrent move:" + action + "::" + temp + "\n");
        System.out.println("   and the action is: " + root.listOfChildren.get(chosenChildIndex).GetValid());
        System.out.println("\n----------");
        
        System.out.println("ALPHABETASEARCH END");
        return action;
    }
    
    public int MaxValue(MinMaxNode node){
    	//maxCounter++;
    	//System.out.println("MaxValue: " + maxCounter + " started at depth " + node.GetNodeDepthLevel());
    	
        if( (node.GetFertility() == false) || (node.GetNodeDepthLevel() == BestAI.maxDepth) ){ //terminal state check
        	//System.out.println("   MaxValue Utility start");
        	return Utility(node);
        }
        /*if(node.GetState().getNextPlayer() == BestAI.playerID)
        	v = 9999;  
        else
        	v = -9999;*/
        
        //System.out.println("   MaxValue " + maxCounter + " for-start");
        for(int i = 0; i < node.listOfChildren.size(); i++){
            if(node.listOfChildren.get(i).GetState().getNextPlayer() == BestAI.playerID){
                v = 9999;
                v = Math.min(v, MaxValue(node.listOfChildren.get(i)));
            }
            else{
                v = -9999;
                v = Math.max(v, MinValue(node.listOfChildren.get(i)));
            }
        }   
	return v;
    }

    public int MinValue(MinMaxNode node){
    	//minCounter++;
    	//System.out.println("MinValue: " + minCounter + " started at depth " + node.GetNodeDepthLevel());
    	
        if( (node.GetFertility() == false) || (node.GetNodeDepthLevel() == BestAI.maxDepth) ){ //terminal state check
            //System.out.println("   MinValue Utility start");
            return Utility(node);
        }
       /*if(node.GetState().getNextPlayer() == BestAI.enemyID)
           v = 9999;  
       else
           v = -9999;*/
       
       //System.out.println("   MinValue " + minCounter + " for-start");
       for(int i = 0; i < node.listOfChildren.size(); i++){
           if(node.listOfChildren.get(i).GetState().getNextPlayer() == BestAI.enemyID){
                v = -9999;
                v = Math.max(v, MinValue(node.listOfChildren.get(i)));
           }
           else{
                v = 9999;
                v = Math.min(v, MaxValue(node.listOfChildren.get(i)));
           }
       }  
	return v;
    }
    
    public int Utility(MinMaxNode node){
    	int returnValue = (node.GetState().getScore(BestAI.playerID)) - (node.GetState().getScore(BestAI.enemyID));  //FIX THIS :I
        //System.out.println("VALUE:" + returnValue);
        return returnValue;
    }
}







