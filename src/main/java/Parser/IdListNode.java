package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  IdListNode.java
 *  @author Xiakan Xu
 */

public class IdListNode {
    private IdNode idNode;
    private IdListNode idListNode;
    private int altNo;

    public IdListNode(){
        this.idNode = null;
        this.idListNode = null;
        this.altNo = 1;
    }

    public void parseIdList(Tokenizer t, boolean declare){
        this.idNode = idNode.parserID(t);

        if(declare){
            if(idNode.isDeclared()){
                // TODO define error type
                System.err.println("Error(Line " + t.lineNum+ "): Variable: " +
                    idNode.getName() + " has been declared.");
                System.exit(3);
            }
            else{
                idNode.setDeclared();
            }
        }


        // second child is id-list
        if(t.currentToken.equals(",")){
            t.nextToken(); // get rid of ','
            this.idListNode = new IdListNode();
            this.idListNode.parseIdList(t, declare);
            this.altNo = 2;
        }
    };

    public void printIdList(){
        idNode.printId();
        if(altNo == 2){
            System.out.print(", ");
            idListNode.printIdList();
        }
    };

    public void execIdList(boolean readMode){
        if(readMode){
            idNode.initialize();
            if(altNo == 2){
                idListNode.execIdList(readMode);
            }
        }
        else{
            idNode.write();
            if(altNo == 2){
                idListNode.execIdList(readMode);
            }
        }

    }

}
