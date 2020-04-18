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

    protected static final Queue<String> queue = new ConcurrentLinkedQueue<>();
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final int processorCoreCount = (Runtime.getRuntime().availableProcessors());
    private static final Path targetPath = Paths.get("res/numbers.txt");

    private static final int regionCount = 3;


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String poll;

        ExecutorService executorService = Executors.newFixedThreadPool(processorCoreCount);

        try {
            for (int i = 1; i <= regionCount; i++)

                executorService.submit(new Generator(i));

            while (counter.get() < regionCount - 1 || queue.size() > 0) {

                if ((poll = queue.poll()) != null)
                {
                    System.out.printf("Регион № %.0f сгенерирован %n", (double) counter.incrementAndGet() / 99 * 100);
                    Files.write(targetPath, poll.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            }
            executorService.shutdown();
            System.out.println((System.currentTimeMillis() - startTime) + " ms\n");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
