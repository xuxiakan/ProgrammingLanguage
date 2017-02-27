package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/25.
 */

public class CompOpNode {
    private String op;
    private int altNo;
    public CompOpNode(){
        this.op = "";
        this.altNo = 0;
    }
    public void  parseCompOp(Tokenizer t){
        this.op = t.currentToken;
        if(this.op.equals("!=")){
            this.altNo = 1;
        }
        else if(this.op.equals("==")){
            this.altNo = 2;
        }
        else if(this.op.equals("<")){
            this.altNo = 3;
        }
        else if(this.op.equals(">")){
            this.altNo = 4;
        }
        else if(this.op.equals("<=")){
            this.altNo = 5;
        }
        else if(this.op.equals(">=")){
            this.altNo = 6;
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): CompOpNode expects a compare operator, " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        t.nextToken(); // get rid of this compare operator
    }

    public void printCompOp(){
        System.out.print(this.op);
    }
}
