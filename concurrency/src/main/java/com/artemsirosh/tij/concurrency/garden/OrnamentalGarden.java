package com.artemsirosh.tij.concurrency.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created at 11-06-2019
 *
 * Has {@link Entrance}s and {@link Count}. Can accumulate data of people
 * visiting in garden.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class OrnamentalGarden {

    private final Count count;
    private final List<Entrance> entrances;

    public OrnamentalGarden() {
        this.count = new Count();
        this.entrances = new ArrayList<>();
    }

    public int getTotalCount() {
        return count.value();
    }

    public Entrance newEntrance(int id) {
        Entrance entrance = new EntranceImpl(id);
        entrances.add(entrance);
        return entrance;
    }

    public int sumEntrances() {
        return entrances.stream()
                .mapToInt(Entrance::getValue)
                .sum();
    }

    private class EntranceImpl implements Entrance {

        private final int id;

        private int number;

        EntranceImpl(int id) {
            this.id = id;
            this.number = 0;
        }

        @Override
        public synchronized int getValue() {
            return number;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (this) {
                        ++number;
                    }

                    System.out.println(this + ", total: " + count.increment() + ", current: " + getValue());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException exc) {
                System.out.println(this+ ": sleep interrupted.");
            }

            System.out.println(this + " stopping.");
        }

        @Override
        public String toString() {
            return "Entrance #" + id;
        }
    }
}
