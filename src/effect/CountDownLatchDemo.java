package effect;

import sort.utils.SortTestHelper;
import sort2.SortUtil;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuh on 2018/4/2.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "yuh's thread " + atomicInteger.getAndIncrement());
            }
        });
        int tasks = 10;
        CountDownLatch ready = new CountDownLatch(tasks);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(tasks);


        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        ready.countDown();
                        start.await();
                        for (int i = 0; i < 100; i++) {
                            Integer[] array = SortTestHelper.generateIntegerArray(10000, -1000, 1000);
                            SortUtil.quickSort(array);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            });
        }
        ready.await();
        start.countDown();
        long startTime = System.currentTimeMillis();
        end.await();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        executor.shutdown();
    }
}
