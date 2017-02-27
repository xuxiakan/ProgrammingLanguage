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

        if(t.currentToken.equals("if")){
            t.nextToken(); // get rid of "if"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): IfNode expects 'if', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        condition.parseCondition(t);

        if(t.currentToken.equals("then")){
            t.nextToken(); // get rid of "then"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): IfNode expects 'then', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        thenSeq.parseStmtSeq(t);
        String token = t.currentToken;
        if(token.equals("else")){
            t.nextToken(); // get rid of "else"
            this.altNo= 2;
            elseSeq= new StmtSeqNode();
            elseSeq.parseStmtSeq(t);
        }
        if(t.currentToken.equals("end")){
            t.nextToken(); // get rid of "end"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): IfNode expects 'end', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): IfNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

    }

    public void printIf(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("if ");
        condition.printCondition();
        System.out.print(" then\n");
        thenSeq.printStmtSeq(tabs+1);
        if (altNo== 2){
            for(int i = 0; i < tabs; i++){
                System.out.print("\t");
            }
            System.out.print("else\n");
            elseSeq.printStmtSeq(tabs+1);
        }
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("end;\n");
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
