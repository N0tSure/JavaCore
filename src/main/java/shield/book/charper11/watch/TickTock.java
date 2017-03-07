package shield.book.charper11.watch;

/**
 * Created by cresh on 24.03.16.
 */
class TickTock {
    String state;
    //boolean running,ticked,tocked;

    /*TickTock() {
        running = true;
        tocked=true;
        ticked=false;
    }*/


    synchronized void tick(boolean running) {
        if (!running) {
            state = "ticked";
            //ticked=true;
            notify();
            return;
        }

        System.out.print("Tick ");
        state="ticked";
        //ticked = true;
        notify();
        try {
            while (!state.equals("tocked")) wait();
        } catch (InterruptedException exc) {
            System.out.println("Thread interrupted.");
        }
    }
    synchronized void tock(boolean running) {
        if (!running) {
            state="tocked";
            notify();
            return;
        }
        System.out.println("Tock");
        state = "tocked";
        notify();
        try {
            while (!state.equals("ticked")) wait();
        } catch (InterruptedException exc) {
            System.out.println("Thread interrupted.");
        }
    }

}
