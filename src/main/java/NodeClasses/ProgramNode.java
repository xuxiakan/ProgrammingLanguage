package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class ProgramNode implements ProgramNode_interface {

    private DeclSeqNode ds;
    private StmtSeqNode ss;
    private boolean initialized;
    public  ProgramNode(){
        this.ds = new DeclSeqNode();
        this.ss= new StmtSeqNode();
        this.initialized = false;
    }
    public void parseProgram(Tokenizer t){
        t.nextToken(); // get rid of "program"
        ds.parseDeclSeq(t);
        if(t.currentToken.equals("begin")){
            t.nextToken(); // get rid of "begin"
        }
        else{
            System.err.println("Parser Error(Line " + t.lineNum+"): expect begin, but currentToken is: '" + t.currentToken + "'");
        }

        ss.parseStmtSeq(t);
        t.nextToken(); // get rid of "end"
        this.initialized = true;
    }
    public void printProgram(){
        if(!this.initialized){
            System.err.println("Cannot print program before parser initialize it..");
            System.exit(2);
        }
        System.out.print("program\n");
        ds.printDeclSeq();
        System.out.print("\tbegin\n");
        ss.printStmtSeq(2);
        System.out.print("\tend\n");
    }

    public void execProgram(){
        ds.execDeclSeq();
        ss.execStmtSeq();
    }


}
