package pack4_task;

import java.io.File;

public interface Interpreter {
    String readFileWithProgram(File file);
    void execProgram(String program);
}
