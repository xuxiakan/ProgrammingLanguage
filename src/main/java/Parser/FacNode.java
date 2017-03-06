package Parser;

import Tokenizer.Tokenizer;


/**
 *  CSE 3341 Programming Assignment 02
 *  FacNode.java
 *  @author Xiakan Xu
 */

class FacNode {
    private IntNode integral;
    private IdNode id;
    private ExpNode exp;
    private int altNo;
    protected FacNode(){
        this.altNo = 0;
        this.integral = null;
        this.id = null;
        this.exp = null;
    }

    protected void parseFac(Tokenizer t){

        if(t.isValidInt(t.currentToken)){
            this.integral = new IntNode();
            this.integral.parseInt(t);
            this.altNo = 1;
        }
        else if(t.isValidIdentifier(t.currentToken)){
            this.id = this.id.parserID(t);
            this.altNo = 2;
        }
        else if(t.currentToken.equals("(")){
            t.nextToken(); // get rid of '('
            this.exp = new ExpNode();
            this.exp.parseExp(t);
            t.nextToken(); // get rid of ')'
            this.altNo = 3;
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): invalid facNode found, " +
                    "currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    protected void printFac(){
        switch (this.altNo){
            case 1:
                this.integral.printInt();
                break;
            case 2:
                this.id.printId();
                break;
            case 3:
                System.out.print("(");
                this.exp.printExp();
                System.out.print(")");
                break;
            default:
                System.err.println("Unexpected error found in printFac.");
                // this error should never happened.
                System.exit(2);
                break;
        }
    }

    protected int evaluateFac(){
        int facValue = 0;
        switch (this.altNo){
            case 1:
                facValue = this.integral.evaluateInt();
                break;
            case 2:
                facValue = this.id.getValue();
                break;
            case 3:
                facValue = this.exp.evaluateExp();
                break;
            default:
                System.err.println("Unexpected error found in evaluateFac.");
                // this error should never happened.
                System.exit(3);
                break;
        }
        return facValue;
    }

}
