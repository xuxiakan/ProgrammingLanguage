package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  StmtNode.java
 *  @author Xiakan Xu
 */

class StmtNode {

    private AssignNode assign;
    private IfNode ifNode;
    private LoopNode loop;
    private InputNode input;
    private OutputNode output;
    private int altNo;

    protected StmtNode(){
        this.assign= null;
        this.ifNode= null;
        this.loop= null;
        this.input= null;
        this.output= null;
        this.altNo= 1;;
    }

    protected void parseStmt(Tokenizer t){
        String tok= t.currentToken;
        //t.nextToken();
         if(tok.equals("if")){ //tok is if
            ifNode= new IfNode();
            altNo= 2;
            ifNode.parseIf(t);
        }
        else if(tok.equals("while")){ // tok is loop
            loop = new LoopNode();
            altNo= 3;
            loop.parseLoop(t) ;
        }
        else if(tok.equals("read")){ // tok is loop
            input = new InputNode();
            altNo= 4;
            input.parseInput(t) ;
        }
        else if(tok.equals("write")){ // tok is loop
            output = new OutputNode();
            altNo= 5;
            output.parseOutput(t) ;
        }
         else if(t.isReservedWords(tok.toLowerCase()) && t.isValidIdentifier(tok)) {
             System.err.println("Parser error(Line " + t.lineNum+"): Assign expects a valid ID, but '" + t.currentToken + "' is a reserved word.");
             System.exit(2);
         }
        else if(t.isValidIdentifier(tok)) {
             assign = new AssignNode();
             altNo = 1;
             assign.parseAssign(t);
         }
        else{
            // should never happen, this error has been checked by StmtSeqNode
            System.err.println("Error: invalid Stmt Node, currentToken is: " + tok + ", line number is " + t.lineNum);
            System.exit(2);
        }
    }

    protected void printStmt(int tabs){
        if(altNo == 1){
            assign.printAssign(tabs);
        }
        else if(altNo == 2){
            ifNode.printIf(tabs);
        }
        else if(altNo == 3){
            loop.printLoop(tabs);
        }
        else if(altNo == 4){
            input.printInput(tabs);
        }
        else if(altNo == 5){
            output.printOutput(tabs);
        }
        else{
            System.err.println("Unexpected error found in printStmt.");
            System.exit(2);
            // should never happen
        }
    }

    protected void execStmt(){
        if(altNo == 1){
            assign.execAssign();
        }
        else if(altNo == 2){
            ifNode.execIf();
        }
        else if(altNo == 3){
            loop.execLoop();
        }
        else if(altNo == 4){
            input.execInput();
        }
        else if(altNo == 5){
            output.execOutput();
        }
        else{
            System.err.println("Debug error: unknown statement found!");
            System.exit(99);
        }
    }
}
