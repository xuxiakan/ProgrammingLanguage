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
        t.nextToken(); // get rid of "read"
        id_list.parseIdList(t);
        t.nextToken(); // get rid of ";"
    }

    public void printInput(){
        System.out.print("read");
        id_list.printIdList();
        System.out.print(";");
    }

    public void execInput(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
    }
}
