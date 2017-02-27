package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class InputNode {
    private IdListNode id_list;
    public InputNode(){
        this.id_list = new IdListNode();
    }

    public void parseInput(Tokenizer t){
        if(t.currentToken.equals("read")){
            t.nextToken(); // get rid of "read"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): InputNode expects 'read', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        id_list.parseIdList(t);
        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ";"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): InputNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printInput(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        System.out.print("read ");
        id_list.printIdList();
        System.out.print(";\n");
    }

    public void execInput(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
