package Parser;

import Tokenizer.Tokenizer;


/**
 *  CSE 3341 Programming Assignment 02
 *  StmtSeqNode.java
 *  @author Xiakan Xu
 */
class StmtSeqNode {
    private StmtNode stmtNode;
    private StmtSeqNode stmtSeqNode;
    private int altNo;

    protected StmtSeqNode(){
        this.stmtNode = new StmtNode();
        this.stmtSeqNode = null;
        altNo = 1;
    }

    protected void parseStmtSeq(Tokenizer t){
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

    protected void printStmtSeq(int tabs) {
        this.stmtNode.printStmt(tabs);
        if (this.altNo == 2) {
            this.stmtSeqNode.printStmtSeq(tabs);
        }
    }


    protected void execStmtSeq(){
        this.stmtNode.execStmt();
        if (this.altNo == 2) {
            this.stmtSeqNode.execStmtSeq();
        }
    }
}
