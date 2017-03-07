package ekkel.book.collections;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cresh on 14.03.16.
 */
public class OperatingStack {
    private static class Command {
        private String line;
        public Command(String line) { this.line=line; }
        public void operation() {
            System.out.print(line);
        }
    }
    private static class Operator {
        private static Queue<Command> queue;
        static {
            Queue<Command> queue = new LinkedList<Command>();
            for (String s : ("Ублюдок, мать твою, а ну иди сюда, говно собачье! Что, решил ко мне лезть?! Ты, засранец " +
                    "вонючий, мать твою, а? Ну, иди сюда, попробуй меня трахнуть, я тебя сам трахну, ублюдок, " +
                    "онанист чертов, будь ты проклят! Иди, идиот, трахать тебя и всю твою семью, говно собачье, " +
                    "жлоб вонючий, дерьмо, сука, падла! Иди сюда, мерзавец, негодяй, " +
                    "гад, иди сюда, ты, говно, ЖОПА!").split(" ")) {
                queue.offer(new Command(s));
            }
            Operator.queue=queue;
        }
        public static Queue<Command> getQueue() {return Operator.queue;}
    }

    public static void main(String[] args) {
        Queue<Command> queue = Operator.getQueue();
        while (queue.peek() != null) {
            System.out.print(" ");
            queue.remove().operation();
        }
    }
}
