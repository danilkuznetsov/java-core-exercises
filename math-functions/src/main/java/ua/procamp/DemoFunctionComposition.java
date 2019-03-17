package ua.procamp;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class DemoFunctionComposition {

    public static void main(String[] args) {
        Function<Integer, Integer> sq = x -> x * x;
        Function<Integer, Integer> inc = x -> x + 1;

        Function<Integer, Integer> sqAnInc = sq.compose(inc);
        Function<Integer, Integer> incAnSq = sq.compose(sq);

        Predicate<String> isNull = s -> s == null;
        Predicate<String> isEmpty = s -> s.isEmpty();

        Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

        // for primitive
        UnaryOperator<Integer> sqpm = x -> x * x;
        IntUnaryOperator incpm = x -> x + 1;

        sqpm.apply(1);
        incpm.applyAsInt(1);

//        Function<Integer, Integer> sqAnInc = sq.compose(inc);
//        Function<Integer, Integer> incAnSq = sq.compose(sq);


        // Method reference

        Function<String, Integer> intParser = Integer::parseInt;

        String hello = "Hello";
        Function<Integer, String> helloRepeatFunction = hello::repeat;

        String helloTenTimes = helloRepeatFunction.apply(10);
        System.out.println(helloTenTimes);

        BiFunction<String, Integer, String> strRep = String::repeat;
        String str = strRep.apply("Mey", 10);

        System.out.println(str);
    }
}
