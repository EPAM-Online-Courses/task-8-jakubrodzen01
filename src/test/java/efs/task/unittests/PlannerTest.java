package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
public class PlannerTest {
    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void testCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {

        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        int actualCalories = planner.calculateDailyCaloriesDemand(TestConstants.TEST_USER, activityLevel);

        assertEquals(expectedCalories, actualCalories);
    }

    @Test
    void testCalculateDailyIntake() {

        DailyIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        DailyIntake actualIntake = planner.calculateDailyIntake(TestConstants.TEST_USER);

        assertEquals(expectedIntake.getCalories(), actualIntake.getCalories());
        assertEquals(expectedIntake.getProtein(), actualIntake.getProtein());
        assertEquals(expectedIntake.getFat(), actualIntake.getFat());
        assertEquals(expectedIntake.getCarbohydrate(), actualIntake.getCarbohydrate());
    }
}
