package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowException_whenHeightIsZero() {
        //given
        double weight = 75.0;
        double height = 0.0;

        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Test BMI dla wzrostu=1.80, wagi={0}")
    @ValueSource(doubles = {83.0, 85.0, 90.0})
    void shouldReturnTrue_whenBMIwithValueSource(double weight) {
        //given
        double height = 1.80;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Test BMI dla wzrostu={0}, wagi={1}")
    @CsvSource({
            "1.80, 59.5",
            "2.80, 75.2",
            "1.72, 69.7"
    })
    void shouldReturnFalse_whenBMIwithCsvSource(double height, double weight) {
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Test BMI dla wzrostu={0}, wagi={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenBMIwithCsvFileSource(double height, double weight) {
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void testFindUserWithTheWorstBMI_WithUserList_ShouldReturnUserWithWorstBMI() {
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);

        assertEquals(1.79, userWithWorstBMI.getHeight());
        assertEquals(97.3, userWithWorstBMI.getWeight());
    }

    @Test
    void testFindUserWithTheWorstBMI_WithEmptyUserList_ShouldReturnNull() {
        User userWithWorstBMI = FitCalculator.findUserWithTheWorstBMI(new ArrayList<>());

        assertNull(userWithWorstBMI);
    }
}