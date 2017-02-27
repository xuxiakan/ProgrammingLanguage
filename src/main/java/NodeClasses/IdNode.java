package NodeClasses;

import java.util.HashMap;
import java.util.Map;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/25.
 */
//TODO make it executable

public class IdNode {
    private String name;
    private int value;
    private boolean initialized;
    private static Map<String, IdNode> symTab = new HashMap<String, IdNode>();

    private IdNode(String n){
        this.name = n;
        this.initialized = false;
    }

    public static IdNode parserID(Tokenizer t){

        String tok = t.currentToken;
        if(!t.isValidIdentifier(tok)){
            System.err.println("Parser error(Line " + t.lineNum+"): expect a valid ID, " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        t.nextToken();
        if(!symTab.containsKey(tok)){
            IdNode node = new IdNode(tok);
            symTab.put(tok, node);
        }
        return symTab.get(tok);
    }

    public  void setValue(int v){
        this.value = v;
        this.initialized = true;
    }

    public int getValue(){
        System.err.println("Get ID value function not Implemented.");
        System.exit(98);
        if(!this.initialized){
            System.err.println("Get ID value function not Implemented.");
            System.exit(98);
        }
        return this.value;
    }

    public  String getName(){
        return this.name;
    }

    public void printId(){
        System.out.print(getName());
    }
}
