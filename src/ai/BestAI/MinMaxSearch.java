package ai.BestAI;

public class MinMaxSearch {
    private int v;		//utility value
    private BestAI BestAIref;
    
    public MinMaxSearch(){
        this.v = 0;
        this.BestAIref = BestAI.GetInstance();
    }
    
    public int AlphaBetaSearch(MinMaxNode root){
        int action = -1;
        int[] vA = new int[6];
        for(int i = 0; i < 6; i++)
        	vA[i] = -9999;
        
        for(int i=0;i<root.listOfChildren.size();i++){
            vA[i] = MinMaxFunction(root.listOfChildren.get(i), -9999999, 9999999);
        }
        
        int bestValue = -99999;
        for(int i=0;i<root.listOfChildren.size();i++){
            if(vA[i] > bestValue && root.listOfChildren.get(i).GetValid()){
               bestValue = vA[i];
               action = root.listOfChildren.get(i).GetChildNr();
            }
        }
        return action;
    }
    
    public int MinMaxFunction(MinMaxNode node, int a, int b){ 	
        if( (node.GetFertility() == false) || (node.GetNodeDepthLevel() == BestAI.maxDepth) ){ //terminal state check
        	return Utility(node);
        }
        
        for(int i = 0; i < node.listOfChildren.size(); i++){ //children loop
            //if is Max and else is Min. Depending on player turns 
            if(node.listOfChildren.get(i).GetState().getNextPlayer() == BestAI.playerID){
                v = 9999;
                v = Math.min(v, MinMaxFunction(node.listOfChildren.get(i), a, b));
                
                if(v >= b)
                	return v;
            
                a = Math.max(a, v);
            }
            else{
                v = -9999;
                v = Math.max(v, MinMaxFunction(node.listOfChildren.get(i), a, b));
                
                if(v <= a)
                	return v;
            
            	b = Math.min(b, v);
            }
        }   
	return v;
    }
    
    public int Utility(MinMaxNode node){
        return (node.GetState().getScore(BestAI.playerID)) - (node.GetState().getScore(BestAI.enemyID)); //Difference in score
    }
}







