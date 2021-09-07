package sortofcomparison;

import java.util.Calendar;
import java.util.Date;

public class MainTest {

    public static void integerInner(Integer x) {
        x = new Integer(3);
    }

    public static void stringInner(String x) {
        x = new String("4");

    }

    public static void dateInner(Date date) {
        date = new Date(1990, Calendar.AUGUST,15);
    }

    public static void main(String[] args) {
        Integer x = new Integer(4);
        integerInner(x);
        System.out.println(x);

        String x1 = new String("5");
        stringInner(x1);
        System.out.println(x1);

        Date date = new Date(2021, Calendar.AUGUST,15);
        dateInner(date);
        System.out.println(date.getYear());

    }
}
