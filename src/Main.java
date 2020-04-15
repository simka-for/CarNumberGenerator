import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static final AtomicInteger counter = new AtomicInteger(0);
    static Queue<String> queue = new ConcurrentLinkedQueue<>();

    private static final int regionsCount = 6;

    public static void main(String[] args) {


        Path targetPath = Paths.get("res/num_multi.txt");
        String str;

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(processorCoreCount);

        try
        {
            for (int i = 1; i < regionsCount; i++)

                executorService.submit(new Generator(i));

            while (counter.get() < regionsCount-1 || Main.queue.size() > 0)
            {
                if ((str = Main.queue.poll()) != null)
                {
                    System.out.printf("Записано %.1f регионов %n",(double) counter.incrementAndGet()/99 * 100);
                    try {
                        Files.write(targetPath, str.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            executorService.shutdown();

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }
}
