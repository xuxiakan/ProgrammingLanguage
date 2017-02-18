package NodeClasses;

import Tokenizer.Tokenizer;

/**
 * Created by xu.1487 on 2017/2/18.
 */

public interface ProgramNode_interface {
    void parseProgram(Tokenizer t);
    void printProgram();
    void execProgram();
}
