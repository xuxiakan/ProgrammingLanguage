package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */
//TODO new implementation
public class DeclSeqNode {
    private DeclNode declNode;
    private DeclSeqNode declSeqNode;
    private int altNo;

    public DeclSeqNode(){
        this.declNode = new DeclNode();
        this.declSeqNode = null;
        altNo = 1;
    }

    public void parseDeclSeq(Tokenizer t){
        declNode.parseDecl(t);

        // TODO: how to check next is decl-seq?
        // if next token is int, then keep parse decl-seq
        if(t.currentToken.equals("int")){
            this.declSeqNode = new DeclSeqNode();
            this.altNo = 2;
            declSeqNode.parseDeclSeq(t);
        }
        else{
            System.out.println("Debug: next token is " + t.currentToken + "make sure its not decl\n");
        }
    };

    public void printDeclSeq(){
        declNode.printDecl();
        if(altNo == 2){
            declSeqNode.printDeclSeq();
        }
    }

    public void execDeclSeq(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
