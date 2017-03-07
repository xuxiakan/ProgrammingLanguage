package Parser;

import java.util.regex.Pattern;

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
    private static int lineNum;
    protected TermNode(){
        this.fac = new FacNode();
        this.term = null;
        this.altNo = 1;
    }

    protected void parseTerm(Tokenizer t){
        this.lineNum = t.lineNum;
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
        isValidInt(termValue);
        return termValue;
    }

    private static void isValidInt(int value){
        if(value > 99999999){
            System.err.print("Execute error(Line " + lineNum+"): intermediate values "+ value + " exceed 8 characters.");
            System.exit(3);
        }
        if(value < 0){
            System.err.print("Execute error(Line " + lineNum+"): intermediate values "+ value + " is not a valid integral.");
            System.exit(3);
        }
    }
}
