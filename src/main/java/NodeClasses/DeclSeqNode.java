package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */
public class DeclSeqNode {
    private DeclNode declNode;
    private DeclSeqNode declSeqNode;
    private int altNo;

    public DeclSeqNode(){
        this.declNode = new DeclNode();
        this.declSeqNode = null;
        this.altNo = 1;
    }

    public void parseDeclSeq(Tokenizer t){
        declNode.parseDecl(t);

        // if next token is int, then keep parse decl-seq
        if(t.currentToken.equals("int")){
            this.declSeqNode = new DeclSeqNode();
            this.declSeqNode.parseDeclSeq(t);
            this.altNo = 2;
        }
    };

    public void printDeclSeq(){
        System.out.print("\t");
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
