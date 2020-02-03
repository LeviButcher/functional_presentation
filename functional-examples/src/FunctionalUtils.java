import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalUtils {

    /**
     * Compose also known as pipe
     *
     * combines two functions together list so:
     * y = b(a)
     * where g and f are the first and second parameters to the function.
     *
     * @param g Function
     * @param f Function
     * @param <A> Input of a
     * @param <B> Output of a and Input to b
     * @param <C> Output of b
     * @return A new function taking a Input of A and return C
     */
    public static <A, B, C> Function<A, C> compose(Function<A, B> g, Function<B, C> f) {
        return (x) -> f.apply(g.apply(x));
    }

    /**
     * Curry allows you to return a new Function that has applied the value arg, the the first parameter
     * of the function f passed in
     *
     * @param f
     * @param arg
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    public static <A, B, C> Function<B, C> curry(BiFunction<A, B, C> f, A arg) {
        return (x) -> f.apply(arg, x);
    }

    /**
     * Takes a list and converts the list down to type B using the provided function
     *
     * The identityValue is the first value passed into function f.
     * If you are summing a list of Ints then the identityValue should zero.
     *
     * @param identityValue
     * @param f
     * @param list
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> B reduce(B identityValue, BiFunction<B, A, B> f, List<A> list) {
        if(list.isEmpty()) {
            return identityValue;
        }

        var result =  f.apply(identityValue, list.get(0));
        return reduce(result, f, list.subList(1, list.size()));
    }

    /**
     *
     *
     * @param f
     * @param list
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> List<B> map(Function<A,B> f, List<A> list) {
        return reduce(List.of(), (acc, curr) -> {
            // This would be much easier if java's List api abide by pure functions
            var result = f.apply(curr);
            return Stream.of(
                    List.of(result).stream(),
                    acc.stream()
            ).flatMap(i -> i).collect(Collectors.toList());
        }, list);
    }

    /**
     *
     *
     * @param f
     * @param list
     * @param <A>
     * @return
     */
    public static <A> List<A> filter(Function<A, Boolean> f, List<A> list) {
        return reduce(List.of(), (acc, curr) -> {
            var shouldReturn = f.apply(curr);
            if(shouldReturn) {
                return Stream.of(
                        acc.stream(),
                        List.of(curr).stream()
                ).flatMap(i -> i).collect(Collectors.toList());
            }
            return acc;
        }, list);
    }
}
