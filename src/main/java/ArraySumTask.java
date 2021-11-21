import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Integer> {

    protected int[] array;
    protected int start;
    protected int end;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        final int diff = end - start;
        return switch (diff) {
            case 0 -> 0;
            case 1 -> array[start];
            case 2 -> array[start] + array[start + 1];
            default -> forkTasksAndGetResult();
        };
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;

        ArraySumTask task1 = new ArraySumTask(array, start, middle);
        ArraySumTask task2 = new ArraySumTask(array, middle, end);

        invokeAll(task1, task2);

        return task1.join() + task2.join();
    }
}
