package pack2_tasks.task9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Island {
    private static List<Integer> islandNumbers = new ArrayList<>();
    public static void main(String[] args) {
        int water;
        islandNumbers = readFile("./src/main/resources/input.txt");
        water = searchWater(islandNumbers);
        System.out.println("Count water: " + water);
    }

    public static int searchWater(List<Integer> island){
        int water = 0;
        int index_left = 0;
        int index_right = island.size() - 1;
        int max_left = 0;
        int max_right = 0;

        while (index_left < index_right){
            if (island.get(index_left) > max_left){
            max_left = island.get(index_left);
            }

            if (island.get(index_right) > max_right) {
                max_right = island.get(index_right);
        }


            if (max_left >= max_right){
                water += max_right - island.get(index_right);
                index_right -= 1;
            } else {
                water += max_left - island.get(index_left);
                index_left += 1;
            }
        }
        return water;
    }

    public static List<Integer> readFile(String path){
        String line;
        List<Integer> island = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null){
                island.add(Integer.parseInt(line));
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return island;
    }
}
