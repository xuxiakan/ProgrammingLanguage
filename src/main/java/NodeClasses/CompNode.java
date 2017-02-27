package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/25.
 */

public class CompNode {
    private FacNode fac1;
    private CompOpNode comp_op;
    private FacNode fac2;

    public CompNode(){
        this.fac1 = new FacNode();
        this.fac2 = new FacNode();
        this.comp_op = new CompOpNode();
    }

    public void parseComp(Tokenizer t){
        if(t.currentToken.equals("(")){
            t.nextToken(); // get rid of '('
        }
        else{
            System.err.println("Parser error(Line " + t.lineNum+"): CompNode expects '(', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        this.fac1.parseFac(t);
        this.comp_op.parseCompOp(t);
        this.fac2.parseFac(t);
        if(t.currentToken.equals(")")){
            t.nextToken(); // get rid of ')'
        }
        else{
            System.err.println("Parser Error(Line " + t.lineNum+"): CompNode expects ')', " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
    }

    public void printComp(){
        System.out.print("(");
        this.fac1.printFac();
        this.comp_op.printCompOp();
        this.fac2.printFac();
        System.out.print(")");
    }

}
