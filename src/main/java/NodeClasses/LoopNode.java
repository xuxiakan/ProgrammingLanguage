package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class LoopNode {
    private CondNode condition;
    private StmtSeqNode statementSeq;

    public LoopNode(){
        this.condition= new CondNode();
        this.statementSeq= new StmtSeqNode();
    }

    public void parseLoop(Tokenizer t){
        t.nextToken(); // get rid of "while"
        condition.parseCondition(t);
        t.nextToken(); // get rid of "loop"
        statementSeq.parseStmtSeq(t);

        t.nextToken(); // get rid of "end"
        t.nextToken(); // get rid of ";"
    }

    public void printLoop(){
        System.out.print("while");
        condition.printCondition();
        System.out.print("loop");
        statementSeq.printStmtSeq();
        System.out.print("end;");
    }

    public void execLoop(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
