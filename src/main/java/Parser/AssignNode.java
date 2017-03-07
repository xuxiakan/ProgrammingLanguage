package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  AssignNode.java
 *  @author Xiakan Xu
 */
class AssignNode {

    private IdNode idNode;
    private ExpNode expNode;
    private int lineNum;

    /**
     * Constructor of AssignNode
     *
     */

    protected AssignNode(){
        this.lineNum = -1;
        this.idNode = null;
        this.expNode = new ExpNode();
    }


    protected void parseAssign(Tokenizer t){
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

    protected void printAssign(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        idNode.printId();
        System.out.print(" = ");
        expNode.printExp();
        System.out.print(";\n");
    }

    protected void execAssign(){
        if(!this.idNode.isDeclared()){
            System.err.println("Execute error(Line " + lineNum+ "): Variable " +
                    idNode.getName() + " undeclared.");
            System.exit(3);
        }
        this.idNode.setValue(this.expNode.evaluateExp());
    }
}
