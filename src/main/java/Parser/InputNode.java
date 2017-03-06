package Parser;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  InputNode.java
 *  @author Xiakan Xu
 */
class InputNode {
    private IdListNode id_list;
    protected InputNode(){
        this.id_list = new IdListNode();
    }

    protected void parseInput(Tokenizer t){
        if(t.currentToken.equals("read")){
            t.nextToken(); // get rid of "read"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): InputNode expects 'read', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        id_list.parseIdList(t, false);
        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): InputNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    protected void printInput(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("read ");
        id_list.printIdList();
        System.out.print(";\n");
    }

    protected void execInput(){

        this.id_list.execIdList(true);
    }
}
