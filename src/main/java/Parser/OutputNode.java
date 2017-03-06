package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  OutputNode.java
 *  @author Xiakan Xu
 */
class OutputNode {
    private IdListNode id_list;
    protected OutputNode(){
        this.id_list = new IdListNode();
    }

    protected void parseOutput(Tokenizer t) {
        if(t.currentToken.equals("write")){
            t.nextToken(); // get rid of "write"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): OutputNode expects 'write', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        id_list.parseIdList(t, false);

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): OutputNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    protected void printOutput(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("write ");
        id_list.printIdList();
        System.out.print(";\n");
    }

    protected void execOutput(){
        this.id_list.execIdList(false);
    }
}
