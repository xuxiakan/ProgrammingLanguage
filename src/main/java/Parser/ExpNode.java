package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  ExpNode.java
 *  @author Xiakan Xu
 */
public class ExpNode {

    private TermNode term;
    private ExpNode exp;
    private int altNo;

    public ExpNode(){
        this.term = new TermNode();
        this.exp = null;
        this.altNo = 1;
    }

    public void parseExp(Tokenizer t){
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

    public void printExp(){
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

    public int evaluateExp(){
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
