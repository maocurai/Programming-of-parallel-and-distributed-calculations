import java.util.function.Function;

public class WorkItem {

    private Function task;

    private int arg;

    private FutureResult future = new FutureResult();

    public WorkItem(Function task, int arg) {
        this.task = task;
        this.arg = arg;
    }

    public Function getTask() {
        return task;
    }

    public void setTask(Function task) {
        this.task = task;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }

    public FutureResult getFuture() {
        return future;
    }

    public void setFuture(FutureResult future) {
        this.future = future;
    }

    public void setDone() {
        this.future.setDone(true);
    }
}
