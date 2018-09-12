import java.util.concurrent.atomic.AtomicLongArray;

public class Main {

    private static class VLong {
        public volatile long value;
        public long a;
    }

    static volatile long x;
    static volatile long y;

    static VLong v1 = new VLong();
    static VLong v2 = new VLong();

    public static void work() {
        x = y  = 0;

        Thread th1 = new Thread(() -> {

            for (int i=0; i<200_000_000; ++i)
               x += i*i;

        });

        Thread th2 = new Thread(() -> {

            for (int i=0; i<200_000_000; ++i)
                y += i*i;

        });


        try {
            long start = System.nanoTime();

            th1.start();
            th2.start();

            th1.join();
            th2.join();

            System.out.println("\t\ttime is: " + (System.nanoTime()-start)/1_000_000_000.0);
            System.out.println(x);
            System.out.println(y);
        }
        catch (InterruptedException ignore) {
        }
    }

    public static void work2() {
        v1.value = v2.value = 0;

        Thread th1 = new Thread(() -> {
            for (int i=0; i<200_000_000; ++i)
                v1.value += i*i;
        });

        Thread th2 = new Thread(() -> {
            for (int i=0; i<200_000_000; ++i)
                v2.value += i*i;
        });


        try {
            long start = System.nanoTime();

            th1.start();
            th2.start();

            th1.join();
            th2.join();

            System.out.println("\t\ttime is: " + (System.nanoTime()-start)/1_000_000_000.0);
            System.out.println(v1.value);
            System.out.println(v2.value);
        }
        catch (InterruptedException ignore) {
        }
    }

    public static void main(String[] args) {
        work();
        work();
        work();

        work2();
        work2();
        work2();
    }
}
