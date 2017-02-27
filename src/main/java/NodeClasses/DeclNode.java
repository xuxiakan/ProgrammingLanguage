package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class DeclNode {
    private IdListNode idList;

    public DeclNode(){
        this.idList = new IdListNode();
    }

    public void parseDecl(Tokenizer t){

        if(t.currentToken.equals("int")){
            t.nextToken(); // get rid of "int"
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): DeclNode expects \"int\", " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        this.idList.parseIdList(t);
        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ';'
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): DeclNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printDecl(){
        System.out.print("int ");
        this.idList.printIdList();
        System.out.print(";\n");
    }

    public void execDecl(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
