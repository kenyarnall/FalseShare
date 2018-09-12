import java.util.concurrent.atomic.AtomicLongArray;

public class Main {
    static volatile long x;
    //static long a;
    static volatile long y;

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

            System.out.println("\t\twork1 time is: " + (System.nanoTime()-start)/1_000_000_000.0);
            System.out.println(x);
            System.out.println(y);
        }
        catch (InterruptedException ignore) {
        }
    }

    public static void work2() {
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

            System.out.println("\t\twork2 time is: " + (System.nanoTime()-start)/1_000_000_000.0);
            System.out.println(x);
            System.out.println(y);
        }
        catch (InterruptedException ignore) {
        }
    }


    public static void main(String[] args) {
        work();
        work();
        work();

//        work2();
//        work2();
//        work2();
    }
}
