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
        t.nextToken(); // get rid of "write"
        id_list.parseIdList(t);
        t.nextToken(); // get rid of ";"
    }

    public void printOutput(){
        System.out.print("write");
        id_list.printIdList();
        System.out.print(";");
    }

    public void execOutput(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
