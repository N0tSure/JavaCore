package shield.book.charper10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cresh on 07.02.16.
 */
public class Help {
    String helpfile;

    public Help(String helpfile) {
        this.helpfile = helpfile;
    }

    boolean helpon(String what) {
        int ch;
        String topic, info;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("helpfile"));
            do {
                ch = reader.read();
                if (ch=='#') {
                    topic=reader.readLine();
                    if (what.compareTo(topic)==0) {
                        do {
                            info=reader.readLine();
                            if (info!=null) System.out.println(info);
                        } while ((info!=null) && (info.compareTo(""))!=0);
                        return true;
                    }
                }
            } while (ch!=-1);
            reader.close();
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc.getMessage());
            return false;
        }
        return false;
    }

    String getSelection() {
        String topic = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the topic: ");

        try {
            topic=reader.readLine();
        } catch (IOException exc) {
            System.out.println("Error while console reading " + exc.getMessage());
        }

        return topic;
    }
}
