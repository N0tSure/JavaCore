package shield.book.charper15;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by cresh on 23.04.16.
 */
public class Test {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.get(Calendar.MILLISECOND);
        System.out.printf("Time : %1$d:%2$d:%3$d %4$d",5,2,3,1);
    }


}
