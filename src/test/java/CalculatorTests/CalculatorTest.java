package CalculatorTests;

import org.example.calculator.Calculator;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {

    static Calculator calc = null;

    @BeforeAll
    public static void setupAll() {
        calc = new Calculator();
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void ShouldAdd(){
        assertEquals(5, calc.add(2,3));
    }

    @Test
    public void ShouldDivide(){
        assertEquals(5, calc.divide(10,2));
    }


}
