package pack2_tasks.task7;
import pack2_tasks.task8.ByteB;
import java.io.*;
import java.util.*;

public class Signals {
    public static Map<ByteB, List<Integer>> signalsTimes = new HashMap<>();
    public static List<Integer> t0 = new ArrayList<>();
    public static List<Integer> t1 = new ArrayList<>();
    public static List<ByteB> signals = new ArrayList<>();

    public static void main(String[] args) {
      readFile();
      parseSignal();
      calcSignalFrequency();

    }

    public static void readFile(){
        int counter = 1;

        try(BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(new File("./src/main/resources/signals.bin")))) {
            int oneByte = buffInputStream.read();

            while (oneByte != -1) {
                if (counter == 1){
                    t0.add(oneByte);
                    counter++;
                } else if(counter == 2){
                    t1.add(oneByte);
                    counter++;
                } else {
                    signals.add(new ByteB(oneByte));
                    counter = 1;
                }
                oneByte = buffInputStream.read();
            }
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        System.out.println("signals: " + signals);
        System.out.println("t1: " + t1 + "\n");

    }

    public static void parseSignal(){
        for (int i = 0; i < signals.size(); i++){
            int t = t1.get(i) - t0.get(i);
            ByteB signal = signals.get(i);
            //System.out.println(signal.getDecByte()+ " " + signal.getBinary());

            if (signalsTimes.containsKey(signal)){
                List<Integer> times = signalsTimes.get(signal);
                times.add(t);
                signalsTimes.replace(signal, times);
            } else {
                List<Integer> times = new ArrayList<>();
                times.add(t);
                signalsTimes.put(signal, times);
            }
        }

    }

    public static void calcSignalFrequency(){

        for (ByteB signal : signalsTimes.keySet()){
            if (signal.getDecByte() != 0) {
                signal.getBinary();
                int second = signal.readBit(2);
                int fourth = signal.readBit(4);
                int seventh = signal.readBit(7);
                int count = second + fourth + seventh;

                int minTime = Collections.min(signalsTimes.get(signal));
                int maxTime = Collections.max(signalsTimes.get(signal));

                double period = (double) (maxTime - minTime) / count;
                double signalFrequency = 1 / period * 1000;

                System.out.printf("Signal %d (%s):  Frequency = %.2f \n", signal.getDecByte(), signal.getBinary(), signalFrequency);
            }
        }
    }
}
