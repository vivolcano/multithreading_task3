import java.util.Date;
import java.util.Random;

public class Main {

    public static Random random = new Random();
    public static int array_size = 5_000;
    public static int[] randomArray = createRandomArray();

    public static void main(String[] args) {

        // подсчет суммы элементов массива однопоточно, с помощью цикла foreach
        long start1 = System.currentTimeMillis();
        int result1 = sumElementsArray(randomArray);
        long time1 = System.currentTimeMillis() - start1;
        printResult(result1, time1);

        // подсчет суммы элементов массива с помощью цикла ForkJoinPool
        long start2 = System.currentTimeMillis();
        ArraySumTask arraySumTask = new ArraySumTask(randomArray, 0, randomArray.length);
        int result2 = arraySumTask.compute();
        long time2 = System.currentTimeMillis() - start2;
        printResult(result2, time2);
    }

    private static void printResult(int result, long time) {
        System.out.println("Сумма элементов массива = " + result + "" +
                "\n Время выполнения сложения = " + time + " мс");
    }

    private static int sumElementsArray(int[] elements) {
        int sum = 0;
        for (int element : elements) {
            sum+= element;
        }
        return sum;
    }

    private static int[] createRandomArray() {
        int[] randArray = new int[array_size];
        for (int i = 0; i < randArray.length; i++) {
            randArray[i] = random.nextInt(100);
        }
        return randArray;
    }
}
