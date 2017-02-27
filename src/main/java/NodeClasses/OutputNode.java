package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class OutputNode {
    private IdListNode id_list;
    public OutputNode(){
        this.id_list = new IdListNode();
    }

    public void parseOutput(Tokenizer t) {
        if(t.currentToken.equals("write")){
            t.nextToken(); // get rid of "write"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): OutputNode expects 'write', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        id_list.parseIdList(t);

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): OutputNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printOutput(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("write ");
        id_list.printIdList();
        System.out.print(";\n");
    }

    public void execOutput(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
