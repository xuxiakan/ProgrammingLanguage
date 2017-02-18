package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public interface IfNode_interface {
    void parseIf(Tokenizer t);
    void printIf();
    void execIf();
}
