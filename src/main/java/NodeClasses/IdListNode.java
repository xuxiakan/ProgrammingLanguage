package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
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

    public void parseIdList(Tokenizer t){
        this.idNode = idNode.parserID(t);
        // second child is id-list
        if(t.currentToken.equals(",")){
            t.nextToken(); // get rid of ','
            this.idListNode = new IdListNode();
            this.idListNode.parseIdList(t);
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

    public void execIdList(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }

}
