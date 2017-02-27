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

        // if next is stmt-seq
        String tok = t.currentToken;
        if(tok.equals("if") || tok.equals("while")|| tok.equals("read")|| tok.equals("write") || t.isValidIdentifier(tok)){//TODO case incomplete
            this.stmtSeqNode = new StmtSeqNode();
            this.altNo = 2;
            this.stmtSeqNode.parseStmtSeq(t);
        }
        else if(!tok.equals("end") && !tok.equals("else")){
            System.err.println("Parser error(Line " + t.lineNum+"): Invalid element \""
                    + t.currentToken + "\" found in Stmt");
            System.exit(2);
        }
    }

    public void printStmtSeq(int tabs) {
        this.stmtNode.printStmt(tabs);
        if (this.altNo == 2) {
            this.stmtSeqNode.printStmtSeq(tabs);
        }
    }


    public void execStmtSeq(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
