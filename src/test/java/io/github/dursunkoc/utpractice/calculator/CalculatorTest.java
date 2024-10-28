package io.github.dursunkoc.utpractice.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    @InjectMocks
    Calculator calculator;

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "2, 3, 5", "3, 4, 7"})
    void testAddWhenPositiveNumbersProvidedShouldReturnSum(int a, int b, int expected) {
        // act
        int result = calculator.add(a, b);
        // assert
        assertEquals(expected, result);
    }

    @Test
    void testAddWhenNegativeNumbersProvidedShouldReturnSum() {
        // arrange
        int expected = -3;
        int a = -1;
        int b = -2;
        // act
        int result = calculator.add(a, b);
        // assert
        assertEquals(expected, result);
    }
}
