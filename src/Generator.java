public class Generator implements Runnable{

    private final int regionCode;

    Generator(int regionCode){
        this.regionCode = regionCode;
    }


    @Override
    public void run() {

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        StringBuilder builder = new StringBuilder();

        for (int number = 1; number < 1000; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");
                    }
                }
            }
        }
        Main.queue.add(builder.toString());
    }

    private static StringBuilder padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        builder.append('0');
        for (int i = 0; i < padSize; i++) {
            builder.append(numberStr);
        }
        return builder;
    }
}
