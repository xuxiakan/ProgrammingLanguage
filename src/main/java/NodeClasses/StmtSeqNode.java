package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 *  TODO: new implementation
 */

public class StmtSeqNode {
    private StmtNode stmtNode;
    private StmtSeqNode stmtSeqNode;
    private int altNo;

    public StmtSeqNode(){
        this.stmtNode = new StmtNode();
        this.stmtSeqNode = null;
        altNo = 1;
    }

    public void parseStmtSeq(Tokenizer t){
        stmtNode.parseStmt(t);

        // TODO: how to check next is stmt-seq?
        // if next is stmt-seq
        if(t.currentToken.equals("stmt-seq")){
            this.stmtSeqNode = new StmtSeqNode();
            this.altNo = 2;
            stmtSeqNode.parseStmtSeq(t);
        }
        else{
            System.out.println("Debug: next token is " + t.currentToken + "make sure its not stmt\n");
        }
    }

    public void printStmtSeq(){
        stmtNode.printStmt();
        if(altNo == 2){
            stmtSeqNode.printStmtSeq();
        }
    }

    public void execStmtSeq(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
