package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  IntNode.java
 *  @author Xiakan Xu
 */

class IntNode {
    private String integral;
    private boolean valid;
    protected IntNode(){
        this.integral = new String();
        this.valid = false;
    }

    protected void parseInt(Tokenizer t){

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

    protected void printInt(){
        if(this.valid){
            System.out.print(this.integral);
        }
        else{
            System.err.print("Parser Error: Cannot print invalid int node");
            System.exit(2);
        }
    }

    protected int evaluateInt(){
        int value = 0;
        if(this.valid){
            value = Integer.parseInt(this.integral);
        }
        else{
            System.err.print("Execute error: integral is invalid");
            System.exit(3);
        }
        return value;
    }
}
