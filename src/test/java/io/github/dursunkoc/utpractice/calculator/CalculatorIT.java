package io.github.dursunkoc.utpractice.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculatorIT {
    @InjectMocks
    Calculator calculator;

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
