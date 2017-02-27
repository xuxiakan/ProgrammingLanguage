package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public interface StmtNode_interface {
    void parseStmt(Tokenizer t);
    void printStmt(int tabs);
    void execStmt();
}
