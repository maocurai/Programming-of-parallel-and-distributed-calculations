import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomExecutor extends Thread {

    private int workersNumber;
    private WorkerThread[] workers;
    private BlockingQueue<WorkItem> tasks;

    public CustomExecutor(int workersNumber) {
        this.workersNumber = workersNumber;
        workers = new WorkerThread[workersNumber];
    }

    public FutureResult[] map(Function func, int[] args) {
        List<WorkItem> collect = Arrays.stream(args)
                .mapToObj(x -> new WorkItem(func, x))
                .collect(Collectors.toList());

        tasks = new ArrayBlockingQueue<>(collect.size(), true, collect);

        return execute();
    }

    public FutureResult[] execute() {
        IntStream.range(0, workersNumber).forEach(x -> workers[x] = new WorkerThread(tasks));
        Arrays.stream(workers).forEach(x -> x.start());
        shutdown();
        return tasks.stream().map(x -> x.getFuture()).toArray(FutureResult[]::new);
    }

    private void shutdown() {
        synchronized (tasks) {
            try {
                tasks.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (Arrays.stream(workers).anyMatch(x -> !x.getState().equals(State.TERMINATED)));
        Arrays.stream(workers).forEach(x -> x.interrupt());
    }
}
