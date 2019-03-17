package ua.procamp;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();


        intFunctionMap.addFunction("abs", Math::abs);

        intFunctionMap.addFunction("increment", i -> i + 1);

        intFunctionMap.addFunction("decrement", i -> i - 1);

        intFunctionMap.addFunction("square", i -> i * i);

        intFunctionMap.addFunction("sgn", i -> {

            if (i == 0) {
                return 0;
            }

            if (i > 0) {
                return 1;
            } else {
                return -1;
            }
        });


        // todo: add simple functions to the function map (abs, sng, increment, decrement, square)

        return intFunctionMap;
    }
}
