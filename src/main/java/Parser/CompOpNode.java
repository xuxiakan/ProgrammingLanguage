package Parser;

import Tokenizer.Tokenizer;


/**
 *  CSE 3341 Programming Assignment 02
 *  CompOpNode.java
 *  @author Xiakan Xu
 */

class CompOpNode {
    private String op;
    private int altNo;
    protected CompOpNode(){
        this.op = "";
        this.altNo = 0;
    }
    protected void  parseCompOp(Tokenizer t){
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

    protected void printCompOp(){
        System.out.print(this.op);
    }

    protected int execCompOp(){
        if(this.altNo == 0){
            // should never happen
            System.err.println("Error: CompOp invalid");
            System.exit(3);
        }
        return this.altNo;
    }
}
