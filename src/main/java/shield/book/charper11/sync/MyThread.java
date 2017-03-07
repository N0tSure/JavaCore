package shield.book.charper11.sync;

/**
 * Created by cresh on 24.03.16.
 */
class MyThread implements Runnable {
    Thread thread;
    int[] nums;
    static SumArray sumArray = new SumArray();
    int answ;

    MyThread(String name, int[] nums) {
        thread = new Thread(this, name);
        this.nums = nums;
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf("%s is running.\n",thread.getName());
        synchronized (sumArray) {
            answ=sumArray.getSum(nums);
        }
        System.out.printf("Answer for %s : %d\n",thread.getName(),answ);
        System.out.printf("%s is stopping.\n",thread.getName());
    }
}
