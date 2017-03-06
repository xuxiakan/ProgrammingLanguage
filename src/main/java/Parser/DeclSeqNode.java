package Parser;

import Tokenizer.Tokenizer;
/**
 *  CSE 3341 Programming Assignment 02
 *  DeclSeqNode.java
 *  @author Xiakan Xu
 */
class DeclSeqNode {
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
        declNode.execDecl();
        if(altNo == 2){
            declSeqNode.execDeclSeq();
        }
    }
}
