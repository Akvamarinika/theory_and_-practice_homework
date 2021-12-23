package pack5_task;

import java.util.*;

public final class MegaHashMap extends HashMap<String, Integer> {
    public final int iloc(int index) {
        Map<String, Integer> sortedMap = toSortedMap();
        int idx = 0;

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()){

            if (idx == index) {
                return entry.getValue();
            }

            idx++;
        }

        throw new MegaHashMapException("Неверно введен индекс!");
    }

    private Map<String, Integer> toSortedMap(){
        return new TreeMap<>(this);
    }

    public final Map<String, Integer> ploc(String condition){
        Map<String, Integer> result = new HashMap<>();
        StringBuilder conditionBuilder = new StringBuilder();
        HashMap<Integer, String> idxCondition = new HashMap<>();

        int counterCondition = 0;
        int position = 0;

        while (position < condition.length()) {
            while (position < condition.length() && (condition.charAt(position) == '<' || condition.charAt(position) == '>' ||
                    condition.charAt(position) == '=' || Character.isDigit(condition.charAt(position)))) {
                conditionBuilder.append(condition.charAt(position));
                position++;
            }

            while (position < condition.length() && !Character.isDigit(condition.charAt(position)) &&
                    !(condition.charAt(position) == '<' || condition.charAt(position) == '>' || condition.charAt(position) == '=')) {
                position++;
            }

            idxCondition.put(counterCondition, conditionBuilder.toString());
            conditionBuilder = new StringBuilder();
            counterCondition++;
        }

        for (Map.Entry<String, Integer> entry :  this.entrySet()) {
            boolean isValid = true;

            if (isInteger(entry.getKey(), counterCondition)) {
                String strKey = entry.getKey().replace("(","").replace(")","");
                HashMap<Integer, String> idxCond = new HashMap<>();
                StringBuilder builder = new StringBuilder();
                position = 0;
                int i = 0;

                while (position < strKey.length()) {
                    while (position < strKey.length() && (strKey.charAt(position) == '<' || strKey.charAt(position) == '>' ||
                            strKey.charAt(position) == '=' || Character.isDigit(strKey.charAt(position)))) {
                        builder.append(strKey.charAt(position));
                        position++;
                    }
                    while (position < strKey.length() && !Character.isDigit(strKey.charAt(position)) &&
                            !(strKey.charAt(position) == '<' || strKey.charAt(position) == '>' || strKey.charAt(position) == '=')) {
                        position++;
                    }

                    idxCond.put(i, builder.toString());
                    builder = new StringBuilder();
                    i++;
                }

                for (i = 0; i < counterCondition; i++){
                    conditionBuilder = new StringBuilder();
                    StringBuilder strBuilder = new StringBuilder();
                    position = 0;

                    while (position < idxCondition.get(i).length()) {
                        while (idxCondition.get(i).charAt(position) == '<' || idxCondition.get(i).charAt(position) == '>' ||
                                idxCondition.get(i).charAt(position) == '=') {
                            conditionBuilder.append(idxCondition.get(i).charAt(position));
                            position++;
                        }

                        while (position < idxCondition.get(i).length() && Character.isDigit(idxCondition.get(i).charAt(position))) {
                            strBuilder.append(idxCondition.get(i).charAt(position));
                            position++;
                        }
                        position++;
                    }

                    switch (conditionBuilder.toString()) {
                        case "<" -> {
                            if (Double.parseDouble(strBuilder.toString()) <= Double.parseDouble(idxCond.get(i))) {
                                isValid=false;
                            }
                        }
                        case ">" -> {
                            if (Double.parseDouble(strBuilder.toString()) >= Double.parseDouble(idxCond.get(i))) {
                                isValid=false;
                            }
                        }
                        case "<=" -> {
                            if (Double.parseDouble(strBuilder.toString()) < Double.parseDouble(idxCond.get(i))) {
                                isValid=false;
                            }
                        }
                        case ">=" -> {
                            if (Double.parseDouble(strBuilder.toString()) > Double.parseDouble(idxCond.get(i))) {
                                isValid=false;
                            }
                        }
                        case "=" -> {
                            if (Double.parseDouble(strBuilder.toString()) != Double.parseDouble(idxCond.get(i))) {
                                isValid=false;
                            }
                        }
                        default -> throw new MegaHashMapException("Вы ввели неверное условие!");
                    }
                }

                if (isValid) {
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return result;
    }


    private boolean isInteger(String key, int counterCondition) {
        int counter = 0;

        for (int i = 0; i < key.toCharArray().length; i++) {
            if (key.toCharArray()[i] == ',') {
                counter++;
            }
        }

        if (counter != counterCondition - 1) {
            return false;
        }

        String condit = key.replace(",", "")
                .replace(" ", "")
                .replace("(","")
                .replace(")","");

        boolean num = true;

        try {
            Double.parseDouble(condit);
        } catch (NumberFormatException ex) {
            num = false;
        }

        return num;
    }
}
