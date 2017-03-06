package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  CompNode.java
 *  @author Xiakan Xu
 */

public class CompNode {
    private FacNode fac1;
    private CompOpNode comp_op;
    private FacNode fac2;

    public CompNode(){
        this.fac1 = new FacNode();
        this.fac2 = new FacNode();
        this.comp_op = new CompOpNode();
    }

    public void parseComp(Tokenizer t){
        if(t.currentToken.equals("(")){
            t.nextToken(); // get rid of '('
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): CompNode expects '(', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        this.fac1.parseFac(t);
        this.comp_op.parseCompOp(t);
        this.fac2.parseFac(t);
        if(t.currentToken.equals(")")){
            t.nextToken(); // get rid of ')'
        }
        else{
            System.err.println("Parser Error(Line " + t.lineNum+"): CompNode expects ')', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printComp(){
        System.out.print("(");
        this.fac1.printFac();
        this.comp_op.printCompOp();
        this.fac2.printFac();
        System.out.print(")");
    }

    public boolean execComp(){
        int fac1 = this.fac1.evaluateFac();
        int fac2 = this.fac2.evaluateFac();
        boolean compResult = false;
        switch (this.comp_op.execCompOp()){
            case 1: compResult = (fac1 != fac2);
                break;
            case 2: compResult = (fac1 == fac2);
                break;
            case 3: compResult = (fac1 < fac2);
                break;
            case 4: compResult = (fac1 > fac2);
                break;
            case 5: compResult = (fac1 <= fac2);
                break;
            case 6: compResult = (fac1 >= fac2);
                break;
            default:
                System.err.println("Compare operator has not initialized.");
                System.exit(3);
        }
        return compResult;
    }
}
