package pack4_task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CowInterpreter implements Interpreter{
    private static final int TOKENS_COUNT = TokenType.values().length;
    private List<TokenType> tokens;
    private List<Integer> memory;
    private int position;
    private Integer register;

    @Override
    public String readFileWithProgram(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null){
                builder.append(line)
                       .append(" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    @Override
    public void execProgram(String program) {
        init();

        tokens = Pattern.compile("\\s+")
                .splitAsStream(program)
                .map(TokenType::convertStrToToken)
                .collect(Collectors.toList());

        ListIterator<TokenType> iterator = tokens.listIterator();

        while (iterator.hasNext()) {
            TokenType token = iterator.next();

            if (token != null) {
                executeInstructions(token, iterator);
            }
        }
    }

    private void init() {
        position = 0;
        tokens = new ArrayList<>();
        memory = new ArrayList<>();
        register = null;
    }

    private void executeInstructions(TokenType token, ListIterator<TokenType> iterator) {
        switch (token) {
            case MoO -> increaseCurrValOfMemory();
            case MOo -> decreaseCurrValOfMemory();
            case moO -> nextBlockOfMemory();
            case mOo -> previousBlockOfMemory();
            case moo -> startOfCycle(iterator);
            case MOO -> endOfCycle(iterator);
            case OOM -> printNumberFromCurrBlock();
            case oom -> inputNumberIntoCurrBlock();
            case mOO -> executeInstructionWithNunFromCurrBlock(iterator);
            case Moo -> ifValInBlockZeroThenEnterElsePrint();
            case OOO -> zeroValueInBlock();
            case MMM -> workWithRegister();
            default -> {}
        }
    }

    private void increaseCurrValOfMemory() {
        writeInMemory(readFromMemory() + 1);
    }

    private void decreaseCurrValOfMemory() {
        writeInMemory(readFromMemory() - 1);
    }

    private void nextBlockOfMemory() {
        position++;
    }

    private void previousBlockOfMemory() {
        if (position == 0) {
            System.exit(-1);
        }

        position--;
    }

    // moo
    private void startOfCycle(ListIterator<TokenType> iterator) {
        if (!iterator.hasPrevious()) {
            System.exit(-1);
        }

        iterator.previous();
        int level = 1;

        while (iterator.hasPrevious() && level > 0) {
            TokenType previous = iterator.previous();

            if (previous == TokenType.moo) {
                level++;
            } else if (previous == TokenType.MOO) {
                level--;
            }
        }

        if (level != 0) {
            System.exit(-1);
        }
    }

    // MOO
    private void endOfCycle(ListIterator<TokenType> iterator) {
        if (readFromMemory() == 0) {
            int level = 1;
            iterator.next();

            if (!iterator.hasNext()) {
                System.exit(-1);
            }

            while (iterator.hasNext() && level > 0) {
                TokenType next = iterator.next();
                if (next == TokenType.MOO) {
                    level++;
                } else if (next == TokenType.moo) {
                    level--;
                }
            }

            if (level != 0) {
                System.exit(-1);
            }
        }
    }

    private void printNumberFromCurrBlock() {
        int value = readFromMemory();
        System.out.println(value);
    }

    private void inputNumberIntoCurrBlock() {
        int number = Input.numberInput();
        writeInMemory(number);
    }

    private void executeInstructionWithNunFromCurrBlock(ListIterator<TokenType> iterator) {
        int tokenIdx = readFromMemory();

        if (tokenIdx == 3 || tokenIdx < 0 || tokenIdx > TOKENS_COUNT) {
            System.exit(0);
        }

        TokenType command = TokenType.getByIndex(tokenIdx);
        executeInstructions(command, iterator);
    }

    private void ifValInBlockZeroThenEnterElsePrint() {
        int symbolAscii;

        if (readFromMemory() == 0) {
            symbolAscii = inputChar();
            writeInMemory(symbolAscii);
        } else {
            symbolAscii = readFromMemory();
            System.out.print((char) symbolAscii);
        }
    }

    private void zeroValueInBlock() {
        writeInMemory(0);
    }

    private void workWithRegister(){
        if (register == null) {
            register = readFromMemory();
        } else {
            writeInMemory(register);
            register = null;
        }
    }

    private int inputChar() {
        char symbol = Input.charInput();
        return (int) symbol;
    }

    public void writeInMemory(int value) {
        increaseMemory();
        memory.set(position, value);
    }

    public int readFromMemory() {
        increaseMemory();

        if (position < 0) {
            return 0;
        }

        return memory.get(position);
    }

    private void increaseMemory() {
        if (memory.size() <= position) {
            for (int i = 0; i <= position - memory.size() + 1; i++) {
                memory.add(0);
            }
        }
    }

}
