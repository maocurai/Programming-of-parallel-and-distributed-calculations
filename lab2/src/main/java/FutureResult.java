import java.time.LocalDateTime;
import java.util.Date;

public class FutureResult<T> {

    private T res;
    private Boolean done = false;
    private Date whenCompleted;

    public FutureResult() {}

    public FutureResult(T res) {
        this.res = res;
    }

    public T get() {
        return res;
    }

    public Boolean isDone() {
        return done;
    }

    public Date getWhenCompleted() {
        return whenCompleted;
    }

    public void setDone(Boolean done) {
        this.whenCompleted = new Date();
        this.done = done;
    }
}
