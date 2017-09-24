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
    private int maxCounter;
    private int minCounter;
    
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
        
        for(int i=0;i<6;i++){
            maxCounter = 0;
            minCounter = 0;
            System.out.println("---------------------");
            System.out.println("AlphaBetaSearch root child " + i);
            vA[i] = MaxValue(root.GetChild(i), -999999, 999999);
            System.out.println("Alt " + (i+1) + ":" + vA[i]);
        }
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("AlphaBetaSearch post root child for");
        
        int temp = -99999;
        
        for(int i=0;i<6;i++){
            if(vA[i] > temp && root.GetChild(i).GetValid()){
               temp = vA[i]; 
               action = i+1;
            }
        }
        
        System.out.print("\nCurrent move:" + action + "::" + counter + "\n");
        System.out.println("\n----------");
        //v = MaxValue(state.clone());
        
        System.out.println("ALPHABETASEARCH END");
        return action;
    }
    
    public int MaxValue(MinMaxNode node, int a, int b){
    	maxCounter++;
    	System.out.println("MaxValue: " + maxCounter + " started at depth " + node.GetNodeDepthLevel());
    	
        if( (node.GetFertility() == false) || (node.GetNodeDepthLevel() == BestAI.maxDepth) ){ //terminal state check
        	System.out.println("   MaxValue Utility start");
        	return Utility(node);
        }
        if(node.GetState().getNextPlayer() == BestAI.playerID)
        	v = 9999;  
        else
        	v = -9999;
        
        System.out.println("   MaxValue " + maxCounter + " for-start");
        for(int i = 0; i < 6; i++){ //1-6
            if(node.GetValid()){
                if(node.GetChild(i).GetState().getNextPlayer() == BestAI.playerID){
                    System.out.println("   v = " + v);

                    System.out.println("   testing node:");
                    System.out.println("      node.fertility = " + node.GetFertility());
                            System.out.println("      node.depth = " + node.GetNodeDepthLevel());

                    System.out.println("   testing child:");
                    MinMaxNode child = node.GetChild(i);
                    System.out.println("      child.fertility = " + child.GetFertility());
                            System.out.println("      child.depth = " + child.GetNodeDepthLevel());
                    System.out.println("   Child acquired");

                    int temp = MaxValue(child,a,b);
                    System.out.println("   PrevMaxValue = " + temp);
                    v = Math.min(v, temp);
                    System.out.println("   WINNER = " + v);
                }
                else{
                    System.out.println("   v = " + v);

                    System.out.println("   testing node:");
                    System.out.println("      node.fertility = " + node.GetFertility());
                            System.out.println("      node.depth = " + node.GetNodeDepthLevel());

                            System.out.println("   testing child:");
                    MinMaxNode child = node.GetChild(i);
                    System.out.println("      child.fertility = " + child.GetFertility());
                            System.out.println("      child.depth = " + child.GetNodeDepthLevel());

                    int temp = MinValue(child,a,b);
                    System.out.println("   PrevMinValue = " + temp);
                    v = Math.max(v, temp);
                    System.out.println("   WINNER = " + v);
                }

                /*if(v >= b)
                    return v;

                a = Math.max(a, v);*/
            }
        }
        System.out.println("   MaxValue " + maxCounter + " for-end");
        
	return v;
    }

    public int MinValue(MinMaxNode node, int a, int b){
    	minCounter++;
    	System.out.println("MinValue: " + minCounter + " started at depth " + node.GetNodeDepthLevel());
    	
        if( (node.GetFertility() == false) || (node.GetNodeDepthLevel() == BestAI.maxDepth) ){ //terminal state check
        	System.out.println("   MinValue Utility start");
        	return Utility(node);
        }
       
       if(node.GetState().getNextPlayer() == BestAI.enemyID)
           v = 9999;  
       else
           v = -9999;
       
       System.out.println("   MinValue " + minCounter + " for-start");
       for(int i = 1; i <= 6; i++){ //1-6
           if(node.GetValid()){
                if(node.GetState().getNextPlayer() == BestAI.enemyID){
                             System.out.println("   v = " + v);

                             System.out.println("   testing node:");
                     System.out.println("      node.fertility = " + node.GetFertility());
                             System.out.println("      node.depth = " + node.GetNodeDepthLevel());

                             System.out.println("   testing child:");
                             MinMaxNode child = node.GetChild(i);
                             System.out.println("      child.fertility = " + child.GetFertility());
                             System.out.println("      child.depth = " + child.GetNodeDepthLevel());
                     System.out.println("   Child acquired");

                             int temp = MinValue(child,a,b);
                             System.out.println("   PrevMinValue = " + temp);
                             v = Math.max(v, temp);
                             System.out.println("   WINNER = " + v);
                }
                else{
                             System.out.println("   v = " + v);

                             System.out.println("   testing node:");
                             System.out.println("      node.fertility = " + node.GetFertility());
                             System.out.println("      node.depth = " + node.GetNodeDepthLevel());

                             System.out.println("   testing child:");
                             MinMaxNode child = node.GetChild(i);
                             System.out.println("      child.fertility = " + child.GetFertility());
                             System.out.println("      child.depth = " + child.GetNodeDepthLevel());
                     System.out.println("   Child acquired");

                             int temp = MaxValue(child,a,b);
                             System.out.println("   PrevMaxValue = " + temp);
                             v = Math.min(v, temp);
                             System.out.println("   WINNER = " + v);
                }

                /*if(v <= a)
                     return v;

                 b = Math.min(b, v);*/
           }
       }
       System.out.println("   MinValue " + minCounter + " for-end");
       
	return v;
    }
    
    public int Utility(MinMaxNode node){
    	//Beh�ver kanske en test om spelet �r �ver och vem som har vunnit
    	int returnValue = (node.GetState().getScore(BestAI.playerID)) - (node.GetState().getScore(BestAI.enemyID));  //FIX THIS :I
    	
    	System.out.println("   Utility returning " + node.GetState().getScore(BestAI.playerID) + " - " + node.GetState().getScore(BestAI.enemyID) + " = " + returnValue);
        return returnValue;
    }
}



























































