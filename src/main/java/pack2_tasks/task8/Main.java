package pack2_tasks.task8;

public class Main {
    public static void main(String[] args) {
        ByteB b = new ByteB(148);
        System.out.println(b.getBinary());
        System.out.println(b.readBit(0));
        System.out.println(b.readBit(1));
        System.out.println(b.readBit(2));
        System.out.println(b.readBit(7));

        b.writeBit(7, 0);
        b.writeBit(0, 1);
        b.writeBit(2, 0);
        System.out.println("b " + b.getBinary());
        System.out.println("b " + b.getDecByte());

        ByteB b2 = new ByteB(255);
        System.out.println("b2 " +  b2.getBinary());

        ByteB b3 = new ByteB(0);
        System.out.println("b3 " + b3.getBinary());

        ByteB b4 = new ByteB(117);
        System.out.println("b4 " + b4.getBinary());
        System.out.println("b4 in Dec: " + b4.getDecByte());

        ByteB b5 = new ByteB(75);
        System.out.println("b5 " + b5.getBinary());

        b.not();
        System.out.println("b not in Binary: " + b.getBinary());
        System.out.println("b not in Dec: " + b.getDecByte());

        b.and(b4);
        System.out.println("b AND b4 in Binary: " + b.getBinary());
        System.out.println("b AND b4 in Binary: " + b.getDecByte());

        b.or(b4);
        System.out.println("b OR b4 in Binary: " + b.getBinary());
        System.out.println("b OR b4 in Dec: " + b.getDecByte());

        try {
            b.add(b4);
            System.out.println("b + b4 in Binary: " + b.getBinary());
            System.out.println("b + b4  in Dec: " + b.getDecByte());

//            b.add(b5);
//            System.out.println("b + b5 in Binary: " + b.getBinary());
//            System.out.println("b + b5 in Dec: " + b.getDecByte());

            b.sub(b5);
            System.out.println("b - b5 in Binary: " + b.getBinary());
            System.out.println("b - b5 in Dec: " + b.getDecByte());

            b.sub(b4);
            System.out.println("b - b4 in Binary: " + b.getBinary());
            System.out.println("b - b4  in Dec: " + b.getDecByte());
        } catch (IllegalArgumentException ex) {
            System.err.println("Error: " + ex.getMessage());
        }


    }
}
