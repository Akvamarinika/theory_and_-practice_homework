package pack2_tasks.task8;

import java.util.Objects;

public class ByteB {
    public static final int MIN_VALUE_DEC = 0;
    public static final int MAX_VALUE_DEC = 255;
    public static final int BINARY_SIZE = 8;
    public static final int BASE_OF_NUM = 2;
    public static final int ZERO_BINARY = 0;
    public static final int ONE_BINARY = 1;
    public static final String PREFIX = "b0";
    private int decByte;
    private StringBuilder builder = new StringBuilder();

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

    public int readBit(int bitNum){    // 0 to 7
        if (bitNum >= 0 && BINARY_SIZE > bitNum){
            return Integer.parseInt(Character.toString(builder.charAt(BINARY_SIZE - 1 - bitNum + 2)));
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

        builder = new StringBuilder(String.valueOf(tmpByte));
        updateDecNumber();
    }

    public void add(ByteB byte2){
        if (this.decByte + byte2.decByte > MAX_VALUE_DEC){
            throw new IllegalArgumentException("Byte overflow!");
        }

        this.decByte = this.decByte + byte2.decByte;
        builder = new StringBuilder();
        this.initBinaryNumber();
    }

    public void sub(ByteB byte2) throws IllegalArgumentException{
        if (this.decByte - byte2.decByte < MIN_VALUE_DEC){
            throw new IllegalArgumentException("Byte overflow!");
        }

        this.decByte = this.decByte - byte2.decByte;
        builder = new StringBuilder();
        this.initBinaryNumber();
    }

    public int getDecByte() {
        return decByte;
    }

    @Override
    public String toString() {
        return Integer.toString(decByte);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteB byteB = (ByteB) o;
        return decByte == byteB.decByte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(decByte);
    }
}
