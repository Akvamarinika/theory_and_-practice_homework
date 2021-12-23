package pack5_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MegaHashMapTest {
    private MegaHashMap megaHashMap;

    @BeforeEach
    void setUp() {
        megaHashMap = new MegaHashMap();

        megaHashMap.put("value1", 1);
        megaHashMap.put("value2", 2);
        megaHashMap.put("value3", 3);
        megaHashMap.put("1", 10);
        megaHashMap.put("2", 20);
        megaHashMap.put("3", 30);
        megaHashMap.put("(1, 5)", 100);
        megaHashMap.put("(5, 5)", 200);
        megaHashMap.put("(10, 5)", 300);
        megaHashMap.put("(1, 5, 3)", 400);
        megaHashMap.put("(5, 5, 4)", 500);
        megaHashMap.put("(10, 5, 5)", 600);
    }

    @Test
    void iloc() {
       assertEquals(100, megaHashMap.iloc(0));
       assertEquals(300, megaHashMap.iloc(2));
       assertEquals(500, megaHashMap.iloc(5));
       assertEquals(30, megaHashMap.iloc(8));

        System.out.println(megaHashMap.iloc(0));
        System.out.println(megaHashMap.iloc(2));
        System.out.println(megaHashMap.iloc(5));
        System.out.println(megaHashMap.iloc(8));
    }

    @Test
    void ploc() {
       assertEquals("{1=10, 2=20, 3=30}", megaHashMap.ploc(">=1").toString());
       assertEquals("{1=10, 2=20}", megaHashMap.ploc("<3").toString());
       assertEquals("{(10, 5)=300, (5, 5)=200, (1, 5)=100}", megaHashMap.ploc(">0; >0").toString());
       assertEquals("{(10, 5)=300}", megaHashMap.ploc(">=10 >0").toString());
       assertEquals("{(1, 5, 3)=400}", megaHashMap.ploc("<5, >=5, >=3").toString());

       System.out.println(megaHashMap.ploc(">=1"));
       System.out.println(megaHashMap.ploc("<3"));
       System.out.println(megaHashMap.ploc(">0; >0"));
       System.out.println(megaHashMap.ploc(">=10 >0"));
       System.out.println(megaHashMap.ploc("<5, >=5, >=3"));
    }
}