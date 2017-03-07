package shield.book.charper10;

/**
 * Created by cresh on 07.02.16.
 */
public class FileHelp {
    public static void main(String[] args) {
        Help ob = new Help("helpfile");
        String topic;

        System.out.println("Bocпoльэyйтecь справочной системой.\n" + "Для выхода из системы введите 'stop'.");
        do {
            topic=ob.getSelection();
            if (!ob.helpon(topic)) System.out.println("Topic not found.\n");
        } while (topic.compareTo("stop")!=0);
    }
}
