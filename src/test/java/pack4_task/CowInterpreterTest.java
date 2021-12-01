package pack4_task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Objects;

class CowInterpreterTest {
    private static ClassLoader classLoader;

    @BeforeAll
    static void beforeAll() {
        classLoader = CowInterpreterTest.class.getClassLoader();
    }

    @Test
    public void testHelloWorld() {
        String path = Objects.requireNonNull(classLoader.getResource("files/hello.cow")).getPath();
        File file = new File(path);
        Interpreter cow = new CowInterpreter();
        String textProgram = cow.readFileWithProgram(file);
        cow.execProgram(textProgram);
    }

    @Test
    public void testHelloShort() {
        String path = Objects.requireNonNull(classLoader.getResource("files/helloShort.cow")).getPath();
        File file = new File(path);
        Interpreter cow = new CowInterpreter();
        String textProgram = cow.readFileWithProgram(file);
        cow.execProgram(textProgram);
    }

    @Test
    public void testFib() {
        String path = Objects.requireNonNull(classLoader.getResource("files/fib.cow")).getPath();
        File file = new File(path);
        Interpreter cow = new CowInterpreter();
        String textProgram = cow.readFileWithProgram(file);
        cow.execProgram(textProgram);
    }

    @Test
    public void testFibGenerator() { //непрерывный генератор
        String path = Objects.requireNonNull(classLoader.getResource("files/fibGenerator.cow")).getPath();
        File file = new File(path);
        Interpreter cow = new CowInterpreter();
        String textProgram = cow.readFileWithProgram(file);
        cow.execProgram(textProgram);
    }
}