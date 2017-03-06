package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  ExpNode.java
 *  @author Xiakan Xu
 */
class ExpNode {

    private TermNode term;
    private ExpNode exp;
    private int altNo;

    protected ExpNode(){
        this.term = new TermNode();
        this.exp = null;
        this.altNo = 1;
    }

    protected void parseExp(Tokenizer t){
        this.term.parseTerm(t);
        if(t.currentToken.equals("+")){
            t.nextToken(); // get rid of '+'
            this.exp = new ExpNode();
            this.exp.parseExp(t);
            this.altNo = 2;
        }
        else if(t.currentToken.equals("-")){
            t.nextToken(); // get rid of '-'
            this.exp = new ExpNode();
            this.exp.parseExp(t);
            this.altNo = 3;
        }
    }

    protected void printExp(){
        this.term.printTerm();
        if(this.altNo == 2){
            System.out.print(" + ");
            this.exp.printExp();
        }
        else if(this.altNo == 3){
            System.out.print(" - ");
            this.exp.printExp();
        }

    }

    protected int evaluateExp(){
        int expValue = this.term.evaluateTerm();
        if(this.altNo == 2){
            expValue += this.exp.evaluateExp();
        }
        else if(this.altNo == 3){
            expValue -= this.exp.evaluateExp();
        }
        return expValue;
    }
}
