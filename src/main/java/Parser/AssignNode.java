package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  AssignNode.java
 *  @author Xiakan Xu
 */

public class AssignNode {

    private IdNode idNode;
    private ExpNode expNode;
    private int lineNum;

    /**
     * Constructor of AssignNode
     *
     */

    public AssignNode(){
        this.lineNum = -1;
        this.idNode = null;
        this.expNode = new ExpNode();
    }


    public void parseAssign(Tokenizer t){
        this.lineNum = t.lineNum;
        this.idNode = idNode.parserID(t);

        if(t.currentToken.equals("=")){
            t.nextToken(); // get rid of '='
        }
        else{
            System.err.println("Parser error(Line " + this.lineNum+"): AssignNode expects '=', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        this.expNode.parseExp(t);

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ';'
        }
        else{
            System.err.println("Parser error(Line " + this.lineNum+"): AssignNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printAssign(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        idNode.printId();
        System.out.print(" = ");
        expNode.printExp();
        System.out.print(";\n");
    }

    public void execAssign(){
        if(!this.idNode.isDeclared()){
            System.err.println("Execute error(Line " + this.lineNum+") Variable: " +
                    idNode.getName() + " undeclared.");
            System.exit(3);
        }
        this.idNode.setValue(this.expNode.evaluateExp());
    }
}
