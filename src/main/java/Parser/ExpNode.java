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
    private static int lineNum;

    protected ExpNode(){
        this.term = new TermNode();
        this.exp = null;
        this.altNo = 1;
    }

    protected void parseExp(Tokenizer t){
        this.lineNum = t.lineNum;
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
        isValidInt(expValue);
        return expValue;
    }

    private static void isValidInt(int value){
        if(value > 99999999){
            System.err.print("Execute error(Line " + lineNum+"): expression value "+ value + " exceed 8 characters.");
            System.exit(3);
        }
        if(value < 0){
            System.err.print("Execute error(Line " + lineNum+"): expression value "+ value + " is not a valid integral.");
            System.exit(3);
        }
    }
}
