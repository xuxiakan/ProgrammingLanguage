package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public class CondNode {

    private int altNo;
    private CompNode compNode;
    private CondNode condition1;
    private CondNode condition2;

    public CondNode(){
        this.altNo = 0;
        this.compNode = null;
        this.condition1 = null;
        this.condition2 = null;
    }

    public void parseCondition(Tokenizer t){
        if(t.currentToken.equals("!")){
            t.nextToken(); // get rid of '!'
            this.altNo = 2;
            this.condition1 = new CondNode();
            this.condition1.parseCondition(t);
        }
        else if(t.currentToken.equals("[")){
            t.nextToken(); // get rid of '['
            this.condition1 = new CondNode();
            this.condition1.parseCondition(t);

            this.condition2 = new CondNode();
            String tok = t.currentToken;
            if(tok.equals("and")){
                this.altNo = 3;
            }
            else if(tok.equals("or")){
                this.altNo = 4;
            }
            else{
                System.err.print("Parser error(Line \" + t.lineNum+\"): unrecognized token "
                        + t + " found in CondNode, expect \"and\" or \"or\".");
                System.exit(2);
            }
            t.nextToken(); // get rid of "and" or "or"
            this.condition2.parseCondition(t);
            if(t.currentToken.equals("]")){
                t.nextToken(); // get rid of ']'
            }
            else{
                System.err.println("Parser error(Line " + t.lineNum+"): CompNode expects ']', " +
                        "but currentToken is: '" + t.currentToken + "'");
                System.exit(2);
            }

        }
        else{
            this.altNo = 1;
            this.compNode = new CompNode();
            this.compNode.parseComp(t);
        }
    }

    public void printCondition(){
        switch(this.altNo){
            case 1: this.compNode.printComp();
                    break;
            case 2: System.out.print("!");
                    this.condition1.printCondition();
                    break;
            case 3: System.out.print("[");
                    this.condition1.printCondition();
                    System.out.print(" and ");
                    this.condition2.printCondition();
                    System.out.print("]");
                    break;
            case 4:System.out.print("[");
                    this.condition1.printCondition();
                    System.out.print(" or ");
                    this.condition2.printCondition();
                     System.out.print("]");
                    break;

            default:System.err.println("Unexpected error found in printCondition.");
                // this error should never happened.
                System.exit(2);
                break;
        }
    }

    public boolean execCondition(){
        //TODO: implement for next Assignment;
        System.err.println("Error: Not implement until next Assignment");
        System.exit(99);
        return false;
    }
}
