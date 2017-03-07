package shield.book.charper12.light;

/**
 * Created by cresh on 31.03.16.
 */
class TrafficLightSimulator implements Runnable {
    private Thread thread;
    private TrafficLightColour colour;
    private boolean stop = false;
    private boolean changed = false;

    TrafficLightSimulator(TrafficLightColour colour) {
        this.colour=colour;
        thread = new Thread(this);
        thread.start();
    }

    TrafficLightSimulator() {
        colour = TrafficLightColour.RED;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                switch (colour) {
                    case GREEN:
                        Thread.sleep(1000);
                        break;
                    case YELLOW:
                        Thread.sleep(200);
                        break;
                    case RED:
                        Thread.sleep(1200);
                        break;
                }
            } catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
            }
            changeColour();
        }
    }

    synchronized void changeColour() {
        switch (colour) {
            case RED:
                colour = TrafficLightColour.GREEN;
                break;
            case YELLOW:
                colour = TrafficLightColour.RED;
                break;
            case GREEN:
                colour = TrafficLightColour.YELLOW;
        }
        //System.out.println("changed=true");
        changed=true;
        notify();
    }

    synchronized void waitForChanges() {
        //System.out.println("waitForChanges()");
        try {
            while (!changed) wait();
            changed=false;
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public String toString() {
        return "TrafficLightSimulator{" +
                "colour=" + colour +
                '}';
    }

    /*String getColour() {
        return colour.toString();
    }*/

    void cancel() {
        stop=true;
    }
}
