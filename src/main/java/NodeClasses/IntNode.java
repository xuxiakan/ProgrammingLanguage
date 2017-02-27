package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/25.
 */

public class IntNode {
    private String integral;
    private boolean valid;
    public IntNode(){
        this.integral = new String();
        this.valid = false;
    }

    public void parseInt(Tokenizer t){

        if(t.isValidInt(t.currentToken)){
            this.integral = t.currentToken;
            this.valid = true;
        }
        else{
            System.err.print("Parser Error: " + t.currentToken + " is not a valid integral.");
            System.exit(2);
        }
        t.nextToken();
    }

    public void printInt(){
        if(this.valid){
            System.out.print(this.integral);
        }
        else{
            System.err.print("Parser Error: Cannot print invalid int node");
            System.exit(2);
        }
    }
}
