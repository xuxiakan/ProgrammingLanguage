package Parser;

import Tokenizer.Tokenizer;
/**
 *  CSE 3341 Programming Assignment 02
 *  ProgramNode.java
 *  @author Xiakan Xu
 */
public class ProgramNode{

    private DeclSeqNode ds;
    private StmtSeqNode ss;
    private boolean initialized;
    public  ProgramNode(){
        this.ds = new DeclSeqNode();
        this.ss= new StmtSeqNode();
        this.initialized = false;
    }
    public void parseProgram(Tokenizer t){

        if(t.currentToken.equals("program")){
            t.nextToken(); // get rid of "program"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): prog expects \"program\", but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        ds.parseDeclSeq(t);
        if(t.currentToken.equals("begin")){
            t.nextToken(); // get rid of "begin"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): prog expects \"begin\", but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        ss.parseStmtSeq(t);

        if(t.currentToken.equals("end")){
            t.nextToken(); // get rid of "end"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): prog expects \"end\", but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        if(!t.currentToken.equals("EOF")){
            System.err.println("Parser error(Line " + t.lineNum+"): extra token " + t.currentToken +" found after prog ends");
            System.exit(2);
        }
        this.initialized = true;
    }
    public void printProgram(){
        if(!this.initialized){
            System.err.println("Cannot print program before parser initialize it..");
            System.exit(2);
        }
        System.out.print("program\n");
        ds.printDeclSeq();
        System.out.print("  begin\n");
        ss.printStmtSeq(2);
        System.out.print("  end\n");
    }

    public void execProgram(){
        if(!this.initialized){
            System.err.println("Program has not been initialized.");
            System.exit(3);
        }
        ds.execDeclSeq();
        ss.execStmtSeq();
    }
}
