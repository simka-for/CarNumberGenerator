import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static final int regionsCount = 2;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(processorCoreCount);

        for (int i = 1; i < regionsCount; i++) {

            executorService.submit(new Generator(i));
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }
}
