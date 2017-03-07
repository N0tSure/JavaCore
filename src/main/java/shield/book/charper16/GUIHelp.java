package shield.book.charper16;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cresh on 10.05.16.
 */
class GUIHelp implements ListSelectionListener {
    private JList<String> list;
    private String result[];
    private JTextArea outputTextArea;


    private String[] fileLoader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("helpfile"))) {
            int i = 0;
            ArrayList<String> topics = new ArrayList<>();
            String topic = "";
            while (i!=-1){
                i = reader.read();
                if(i=='#') {
                    topic = reader.readLine();
                    if (!topic.equals("")) topics.add(topic);
                }
            }
            result = new String[topics.size()];
            for (int j = 0; j < result.length; j++) {
                result[j]=topics.get(j);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    private void fileReader(String topic) {
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
                            outputTextArea.append(info+"\n");
                        } while (!(info.equals("")) && info!=null);
                        break;
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int i = list.getSelectedIndex();
        if (listSelectionEvent.getValueIsAdjusting()) fileReader(result[i]);
    }

    private GUIHelp() {
        JFrame frame = new JFrame("Help with GUI");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        String[] topics = fileLoader();
        list = new JList<>(topics);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        JScrollPane jScrollPane = new JScrollPane(list);

        JLabel title = new JLabel("Choose a topic:");

        outputTextArea = new JTextArea();
        outputTextArea.setRows(10);
        JScrollPane textAreaPane = new JScrollPane(outputTextArea);
        //outputLabel = new JLabel("Text plane");
        //JLabel newLable = new JLabel("Test text");

        frame.add(title, BorderLayout.NORTH);
        frame.add(jScrollPane, BorderLayout.CENTER);
        frame.add(textAreaPane, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIHelp();
            }
        });
    }
}
