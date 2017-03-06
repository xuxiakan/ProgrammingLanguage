package Parser;

import Tokenizer.Tokenizer;


/**
 *  CSE 3341 Programming Assignment 02
 *  TermNodee.java
 *  @author Xiakan Xu
 */
class TermNode {
    private FacNode fac;
    private TermNode term;
    private int altNo;
    protected TermNode(){
        this.fac = new FacNode();
        this.term = null;
        this.altNo = 1;
    }

    protected void parseTerm(Tokenizer t){
        this.fac.parseFac(t);
        if(t.currentToken.equals("*")){
            t.nextToken(); // get rid of '*'
            this.term = new TermNode();
            this.term.parseTerm(t);
            this.altNo = 2;
        }
    }

    protected void printTerm(){
        this.fac.printFac();
        if(this.altNo == 2){
            System.out.print(" * ");
            this.term.printTerm();
        }
    }

    protected int evaluateTerm(){
        int termValue = this.fac.evaluateFac();
        if(this.altNo == 2){
            termValue = termValue*this.term.evaluateTerm();
        }
        return termValue;
    }
}
