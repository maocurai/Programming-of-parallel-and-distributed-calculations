import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static Function<Integer, Double> getSquareRoot = x -> Math.sqrt(x);

    public static void main(String[] args) {

        int tasks [] = new int[]{ 1, 4, 9, 16, 25, 36 };
        CustomExecutor customExecutor = new CustomExecutor(2);
        System.out.println("Number of workers: 2\nTime in nano  Result");
        Arrays.stream(customExecutor.map(getSquareRoot, tasks))
                .forEach(x -> System.out.println(x.getWhenCompleted().getTime() + " " + x.get()));
    }
}
