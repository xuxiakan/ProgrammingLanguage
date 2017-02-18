package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class StmtNode implements StmtNode_interface {

    private AssignNode assign;
    private IfNode ifNode;
    private LoopNode loop;
    private InputNode input;
    private OutputNode output;
    private int altNo;

    public StmtNode(){
        this.assign= null;
        this.ifNode= null;
        this.loop= null;
        this.input= null;
        this.output= null;
        this.altNo= 1;;
    }

    public void parseStmt(Tokenizer t){
        String tok= t.currentToken;
        t.nextToken();
        if(tok == "id"){ //tok is an id
            assign = new AssignNode();
            altNo= 1;
            assign.parseAssign(t);
        }
        else if(tok.equals("if")){ //tok is if
            ifNode= new IfNode();
            altNo= 2;
            ifNode.parseIf(t);
        }
        else if(tok.equals("loop")){ // tok is loop
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
        else{
            //TODO: error message
            System.err.println("Error");
        }
    }

    public void printStmt(){
        if(altNo == 1){
            assign.printAssign();
        }
        else if(altNo == 2){
            ifNode.printIf();
        }
        else if(altNo == 3){
            loop.printLoop();
        }
        else if(altNo == 4){
            input.printInput();
        }
        else if(altNo == 5){
            output.printOutput();
        }
        else{
            System.err.println("Error: this error should never happened, debug code!");
        }
    }

    public void execStmt(){
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
            System.err.println("Error: this error should never happened, debug code!");
        }
    }
}
