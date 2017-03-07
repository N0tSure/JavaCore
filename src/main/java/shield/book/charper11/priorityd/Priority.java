package shield.book.charper11.priorityd;

/**
 * Created by cresh on 22.03.16.
 */
class Priority implements Runnable {
    Thread thread;
    int count;
    static String currentName;
    static boolean stop = false;

    Priority(String name) {
        thread = new Thread(this,name);
        count = 0;
        currentName = name;
    }

    @Override
    public void run() {
        System.out.printf("%s - запуск.\n",thread.getName());
        do {
            count++;
            /*if (currentName.compareTo(thread.getName())!=0) {
                currentName=thread.getName();
                System.out.printf("В %s\n",thread.getName());
            }*/

            if (!(currentName.equals(thread.getName()))) {
                currentName=thread.getName();
                System.out.printf("В %s\n",thread.getName());
            }
        } while (stop==false && count < 1000000);
        stop=true;
        System.out.printf("%s - завершен.\n",thread.getName());
    }
}
