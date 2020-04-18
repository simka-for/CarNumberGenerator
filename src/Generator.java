import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generator implements Runnable{

    private final int regionCode;

    Generator(int regionCode) throws FileNotFoundException {
        this.regionCode = regionCode;
    }

    private final PrintWriter writer = new PrintWriter("res/num_multi.txt");


    @Override
    public void run() {

        long start = System.currentTimeMillis();

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            StringBuilder builder = new StringBuilder();
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                            writer.write(builder.toString());
                        }
                    }
                }
            }
        writer.flush();
        writer.close();
        System.out.println("Регион №" + getRegionCode()+ " сгенерирован. " + (System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }

    public int getRegionCode() {
        return regionCode;
    }
}
