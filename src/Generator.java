import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Generator implements Runnable{

    private final int regionCode;

    Generator(int regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public void run() {

        Path targetPath = Paths.get("res/numbersRegion" + regionCode + ".txt");

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        StringBuilder builder = new StringBuilder();
        for (char firstLetter : letters) {
            for (char secondLetter : letters) {
                for (char thirdLetter : letters) {
                    for (int number = 1; number < 1000; number++) {
                        builder.append(firstLetter)
                                .append(padNumber(number, 3))
                                .append(secondLetter).append(thirdLetter)
                                .append(padNumber(regionCode, 2))
                                .append("\n");
                    }
                }
            }
        }
        try {
            Files.write(targetPath, builder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Регион № " + regionCode + " сгенерирован");
    }

    private static String padNumber(int number, int numberLength) {

        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
    }

}
