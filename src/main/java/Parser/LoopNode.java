package Parser;

import Tokenizer.Tokenizer;
/**
 *  CSE 3341 Programming Assignment 02
 *  LoopNode.java
 *  @author Xiakan Xu
 */

class LoopNode {
    private CondNode condition;
    private StmtSeqNode statementSeq;

    protected LoopNode(){
        this.condition= new CondNode();
        this.statementSeq= new StmtSeqNode();
    }

    protected void parseLoop(Tokenizer t){
        if(t.currentToken.equals("while")){
            t.nextToken(); // get rid of "while"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): LoopNode expects 'while', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        condition.parseCondition(t);

        if(t.currentToken.equals("loop")){
            t.nextToken(); // get rid of "loop"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): LoopNode expects 'loop', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        statementSeq.parseStmtSeq(t);

        if(t.currentToken.equals("end")){
            t.nextToken(); // get rid of "end"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): LoopNode expects 'end', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): LoopNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

    }

    protected void printLoop(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("while ");
        condition.printCondition();
        System.out.print(" loop\n");
        statementSeq.printStmtSeq(tabs + 1);
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("end;\n");
    }

    protected void execLoop(){

        while(this.condition.execCondition()){
            statementSeq.execStmtSeq();
        }
    }
}
