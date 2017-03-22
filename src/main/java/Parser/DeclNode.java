package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  DeclNode.java
 *  @author Xiakan Xu
 */
class DeclNode {
    private IdListNode idList;

    protected DeclNode(){
        this.idList = new IdListNode();
    }

    protected void parseDecl(Tokenizer t){

        if(t.currentToken.equals("int")){
            t.nextToken(); // get rid of "int"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): DeclNode expects \"int\", " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        this.idList.parseIdList(t, true);
        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ';'
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): DeclNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    protected void printDecl(){
        System.out.print("int ");
        this.idList.printIdList();
        System.out.print(";\n");
    }

    protected void execDecl(){

    }
}
