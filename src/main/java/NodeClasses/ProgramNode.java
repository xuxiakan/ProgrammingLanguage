package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class ProgramNode implements ProgramNode_interface {

    private DeclSeqNode ds;
    private StmtSeqNode ss;
    public  ProgramNode(){
        this.ds = new DeclSeqNode();
        this.ss= new StmtSeqNode();
    }
    public void parseProgram(Tokenizer t){
        t.nextToken(); // why?
        ds.parseDeclSeq(t);
        t.nextToken(); // why?
        ss.parseStmtSeq(t);
        t.nextToken(); // why?
    }
    public void printProgram(){
        System.out.print("program");
        ds.printDeclSeq();
        System.out.print("begin");
        ss.printStmtSeq();
        System.out.print("end");
    }

    public void execProgram(){
        ds.execDeclSeq();
        ss.execStmtSeq();
    }


}
