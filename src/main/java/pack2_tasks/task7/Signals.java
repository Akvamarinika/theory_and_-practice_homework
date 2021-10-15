package pack2_tasks.task7;

import java.io.*;

public class Signals {
    public static void main(String[] args) {
        readFile();
    }

    public static void readFile(){
        try(BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(new File("./src/main/resources/signals.bin")))) {
            byte[] b = buffInputStream.readNBytes(3);
            System.out.println(Integer.toBinaryString(b[0]));
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}
