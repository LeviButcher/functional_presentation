import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge {

    @Test
    public void functional_method_challenge() {
        var expectedAverage = 46;
        var expectedOfflineCount = 4;

        var satelliteData = List.of("48", "20", "", "100", "18", "47", "", "", "");
        var onlineSat = FunctionalUtils.filter(x -> !x.isEmpty(), satelliteData);
        var onlineSatNumbers = FunctionalUtils.map(Integer::valueOf, onlineSat);
        var offlineSatCount = FunctionalUtils.filter(String::isEmpty, satelliteData).size();
        var onlineLength = onlineSat.size();
        var onlineAverage = FunctionalUtils.reduce(0, Calculator::add, onlineSatNumbers) / onlineLength;

        assertEquals(expectedAverage, onlineAverage);
        assertEquals(expectedOfflineCount, offlineSatCount);
    }
}
