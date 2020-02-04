import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionalUtilsTest {

    public static int divideByFive(int a) {
        return a / 5;
    }

    public static int multiplyByFive(int a) {
        return a * 5;
    }

    @Test
    public void Compose_Pass_In_Funcs_a_And_b_Should_Return_Expected() {
        var expectedResult = 5;
        var multiplyThenDivideByFive = FunctionalUtils.compose(FunctionalUtilsTest::multiplyByFive, FunctionalUtilsTest::divideByFive);
        var result = multiplyThenDivideByFive.apply(5);
        assertEquals(expectedResult, result);
    }

    @Test
    public void Curry_Should_Return_Curried_Function() {
        var expectedResult = 5;
        var multiplyByFive = FunctionalUtils.curry(Calculator::multiply, 5);
        assertEquals(multiplyByFive(1), 5);
        var divideByFive = FunctionalUtils.curry(Calculator::divide, 5);
        assertEquals(divideByFive(5), 1);
        var divideByFiveThenMultiplyByFive = FunctionalUtils.compose(divideByFive, multiplyByFive);
        var result = divideByFiveThenMultiplyByFive.apply(5);
        assertEquals(expectedResult, result);
    }

    @Test
    public void Reduce_Should_Return_Expected() {
        var expectedResult = 4;
        var list = List.of(4);
        var result = FunctionalUtils.reduce(0, Integer::sum, list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void Map_Should_Return_Expected() {
        var expectedResult = List.of(4, 4);
        var list = List.of(2,2);;
        var multiplyBy2 = FunctionalUtils.curry(Calculator::multiply, 2);
        var result = FunctionalUtils.map(multiplyBy2,  list);
        assertEquals(expectedResult, result);
    }

    @Test
    public void Filter_Should_Return_Expected() {
        var expectedResult = List.of(2,4,6);
        var list = List.of(2,4,6,7,1);
        var result = FunctionalUtils.filter((a) -> a % 2 == 0, list);
        assertEquals(expectedResult, result);
    }
}
