package shield.book.charper16.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cresh on 11.05.16.
 */
class Main {
    StringBuilder pane;
    String topic;

    private Main() {
        pane = new StringBuilder();
        topic = "switch";
    }

    private void setTopic(String line) {
        this.topic = line;
    }

    private void fileReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("helpfile"))) {
            int i;
            String info=null, prompt = null;
            while (true) {
                i = reader.read();
                if (i=='#') {
                    prompt = reader.readLine();
                    if (prompt.equals(topic)) {
                        do {
                            info=reader.readLine();
                            pane.append(info);
                        } while (!(info.equals("")));
                        break;
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.fileReader();
        System.out.println(main.pane);

        main.setTopic("while");
        main.fileReader();
        System.out.println(main.pane);
    }
}
