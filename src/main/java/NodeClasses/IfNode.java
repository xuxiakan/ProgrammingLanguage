package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class IfNode implements IfNode_interface {
    private CondNode condition;
    private StmtSeqNode thenSeq;
    private StmtSeqNode elseSeq;
    private int altNo;

    public IfNode(){
        this.condition= new CondNode();
        this.thenSeq= new StmtSeqNode();
        this.elseSeq= null;
        this.altNo= 1;
    }

    public void parseIf(Tokenizer t){
        t.nextToken(); // get rid of "if"
        condition.parseCondition(t);
        t.nextToken(); // get rid of "then"
        thenSeq.parseStmtSeq(t);
        String token = t.currentToken;
        if(token.equals("else")){
            t.nextToken(); // get rid of "then"
            this.altNo= 2;
            elseSeq= new StmtSeqNode();
            elseSeq.parseStmtSeq(t);
        }

        t.nextToken(); // get rid of "end"
        t.nextToken(); // get rid of ";"
    }

    public void printIf(){
        System.out.print("if");
        condition.printCondition();
        System.out.print("then");
        thenSeq.printStmtSeq();
        if (altNo== 2){
            System.out.print("else");
            elseSeq.printStmtSeq();
        }
        System.out.print("end;");
    }

    // TODO: check this implement in next Assignment
    public void execIf(){
        boolean c = condition.execCondition();
        if (c){
            thenSeq.execStmtSeq();
        }

        else if (altNo== 2){
            elseSeq.execStmtSeq();
        }

    }
}
