import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class FirstClassTest {

    @Test
    void FirstClass_Functions_Store_Add_In_Var_Should_Return_Expected() {
        // Much easier to do in Functional Languages
        var expectedResult = 5;
        BiFunction<Integer, Integer, Integer> add = Calculator::add;
        var result = add.apply(4, 1);
        assertEquals(expectedResult, result);
    }
}