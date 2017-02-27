package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class AssignNode {
    private IdNode idNode;
    private ExpNode expNode;

    public AssignNode(){
        this.idNode = null;
        this.expNode = new ExpNode();
    }

    public void parseAssign(Tokenizer t){
        this.idNode = idNode.parserID(t);

        if(t.currentToken.equals("=")){
            t.nextToken(); // get rid of '='
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): AssignNode expects '=', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }

        this.expNode.parseExp(t);

        if(t.currentToken.equals(";")){
            t.nextToken(); // get rid of ';'
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): AssignNode expects ';', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printAssign(int tabs){
        for(int i = 0; i < tabs; i++){
            System.out.print("\t");
        }
        idNode.printId();
        System.out.print(" = ");
        expNode.printExp();
        System.out.print(";\n");
    }

    public void execAssign(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
