package classes;

import java.util.LinkedList;
import java.util.Queue;

public class semaphore {
    private int value;
    private Queue<Integer> queue = new LinkedList<>();

    public semaphore(int initialPermits) {
        this.value = initialPermits;
    }

    public synchronized boolean tryacquire() {
        if (value > 0) {
            value--;
            return true;
        }
        return false;
    }

    public synchronized void Acquire(int carId) throws InterruptedException {
        while (value <= 0) {
            queue.add(carId);
            wait();
        }
        value--;
    }

    public synchronized void Release() {
        value++;
        if (!queue.isEmpty()) {
            queue.poll();
            notify();
        }
    }
}
