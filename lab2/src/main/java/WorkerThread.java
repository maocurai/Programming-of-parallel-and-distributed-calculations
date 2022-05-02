import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

    private BlockingQueue<WorkItem> tasks;

    public WorkerThread(BlockingQueue<WorkItem> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        WorkItem task = null;
        while (true) {
            synchronized (tasks) {
                if(task != null) tasks.add(task);
                if (tasks.peek().getFuture().isDone()) {
                    tasks.notify();
                    return;
                }
                task = tasks.poll();
            }
            try {
                task.setFuture(new FutureResult<>((Double) task.getTask().apply(task.getArg())));
                task.setDone();
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}