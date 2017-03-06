package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  IdListNode.java
 *  @author Xiakan Xu
 */
class IdListNode {
    private IdNode idNode;
    private IdListNode idListNode;
    private int altNo;

    protected IdListNode(){
        this.idNode = null;
        this.idListNode = null;
        this.altNo = 1;
    }

    protected void parseIdList(Tokenizer t, boolean declare){
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

    protected void printIdList(){
        idNode.printId();
        if(altNo == 2){
            System.out.print(", ");
            idListNode.printIdList();
        }
    };

    protected void execIdList(boolean readMode){
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
