package pack2_tasks.task8;

import java.util.Arrays;

public class ByteB {
    public static final int MIN_VALUE_DEC = 0;
    public static final int MAX_VALUE_DEC = 255;
    public static final int BINARY_SIZE = 8;
    public static final int BASE_OF_NUM = 2;
    public static final int ZERO_BINARY = 0;
    public static final int ONE_BINARY = 1;
    public static final String PREFIX = "b0";
    private int decByte;
    //int[] binaryByte = new int[8];
    private StringBuilder builder = new StringBuilder();

   // public static final byte MIN_PLUS_VALUE = 0;
  //  public static final byte MAX_PLUS_VALUE = 255;


    public ByteB(int val) {
        this.decByte = val;

        if (!isOverflow()){
            initBinaryNumber();
        } else {
            throw new IllegalArgumentException("Sorry, there was an overflow.");
        }

    }

    private boolean isOverflow(){
        return !((decByte >= MIN_VALUE_DEC) && (decByte <= MAX_VALUE_DEC));
    }

    private void initBinaryNumber(){
        int tmpByte = decByte;
        int remainder;

        while (tmpByte != 0){
            remainder = tmpByte % BASE_OF_NUM;
            tmpByte = tmpByte / BASE_OF_NUM;

            if (remainder == ONE_BINARY){
                builder.append(ONE_BINARY);
            } else {
                builder.append(ZERO_BINARY);
            }
        }


        padBinaryNumberWithZeros();

    }

    private void updateDecNumber(){
        int powN = 0;
        int tmpDec = 0;
        char[] tmpBinary = builder.toString().toCharArray();

        for (int i = BINARY_SIZE+1; i > 1; i--) {
            int bit = Integer.parseInt(String.valueOf(tmpBinary[i]));

            if (bit == 1){
                tmpDec += (bit) * (int)Math.pow(BASE_OF_NUM, powN);
            }

            powN++;
        }
        decByte = tmpDec;
    }

    private void padBinaryNumberWithZeros(){
        while (builder.length() < BINARY_SIZE) {
            builder.append(ZERO_BINARY);
        }
        builder.append(PREFIX);
        builder.reverse();
    }


    public void writeBit(int bitNum, int value){
        if ((bitNum >= 0 && BINARY_SIZE > bitNum) && ((value == ZERO_BINARY) || (value == ONE_BINARY))){
            builder.replace(BINARY_SIZE - 1 - bitNum + 2, BINARY_SIZE - 1 - bitNum + 3, String.valueOf(value));
            updateDecNumber();
        } else {
            throw new IllegalArgumentException("The bit number can only be from 0 to 7, inclusive.");
        }

    }  // 0 to 7

    public char readBit(int bitNum){    // 0 to 7
        if (bitNum >= 0 && BINARY_SIZE > bitNum){
            return builder.charAt(BINARY_SIZE - 1 - bitNum + 2);
        } else {
            throw new IllegalArgumentException("The bit number can only be from 0 to 7, inclusive.");
        }
    }

    public String getBinary(){
        return builder.toString();
    }



    public void and(ByteB byte2){
        char[] tmpByte = builder.toString().toCharArray();
        char[] tmpByte2 = byte2.getBinary().toCharArray();
        String itemB;
        String itemB2;
        for (int i = 2; i < tmpByte.length; i++){
            itemB = String.valueOf(tmpByte[i]);
            itemB2 = String.valueOf(tmpByte2[i]);

            if (itemB2.equals("1") && itemB.equals("1")){
                tmpByte[i] = '1';
            } else {
                tmpByte[i] = '0';
            }

        }

        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public void or(ByteB byte2){
        char[] tmpByte = builder.toString().toCharArray();
        char[] tmpByte2 = byte2.getBinary().toCharArray();
        String itemB;
        String itemB2;
        for (int i = 2; i < tmpByte.length; i++){
            itemB = String.valueOf(tmpByte[i]);
            itemB2 = String.valueOf(tmpByte2[i]);
            if (itemB2.equals("1") || itemB.equals("1")){
                tmpByte[i] = '1';
            } else {
                tmpByte[i] = '0';
            }

        }
        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public void not(){
        char[] tmpByte = builder.toString().toCharArray();
        String item;
        for (int i = 2; i < tmpByte.length; i++){
            item = String.valueOf(tmpByte[i]);
            if (item.equals("0")){
                tmpByte[i] = '1';
            } else {
                tmpByte[i] = '0';
            }

        }
        //System.out.println(tmpByte);
        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public void add(ByteB byte2){
        char[] tmpByte = builder.toString().toCharArray();
        char[] tmpByte2 = byte2.getBinary().toCharArray();
        String bit;
        String bit2;

        for (int i = tmpByte2.length-2; i > 1; i--){
            bit = String.valueOf(tmpByte[i]);
            bit2 = String.valueOf(tmpByte2[i]);

            if ((bit.equals("1") && bit2.equals("0")) || (bit.equals("0") && bit2.equals("1"))){
                tmpByte[i] = '1';
            } else if (bit.equals("1") && bit2.equals("1")){
                for (int j = tmpByte2.length; j > i; j--){
                    if (String.valueOf(tmpByte[j]).equals("0")){
                        tmpByte[i] = '1';
                    } else if (String.valueOf(tmpByte[j]).equals("b")){
                        throw new IllegalArgumentException("Byte overflow!");
                    }
                }
            } else {
                tmpByte[i] = '0';
            }

        }
        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public void sub(ByteB byte2){
        char[] tmpByte = builder.toString().toCharArray();
        char[] tmpByte2 = byte2.getBinary().toCharArray();
        String itemB;
        String itemB2;
        for (int i = tmpByte.length; i > 1; i--){
            itemB = String.valueOf(tmpByte[i]);
            itemB2 = String.valueOf(tmpByte2[i]);
            if (itemB2.equals("1") || itemB.equals("1")){
                tmpByte[i] = '1';
            } else {
                tmpByte[i] = '0';
            }

        }
        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public int getDecByte() {
        return decByte;
    }
}
