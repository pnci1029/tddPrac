package com.example.wtc.junit;

import org.hibernate.annotations.Parameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.security.RunAs;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ParameterizedTest {
    private double expectedOne;
    private double expectedTwo;
    private double result;

    public static Collection<Integer[]> getTestParameters() {
        return Arrays.asList(new Integer[][]{
                {2, 1, 3},
                {1, 2, 3},
                {5, 5, 10}
        });
    }
    public ParameterizedTest(double expectedOne, double expectedTwo, double result) {
        this.expectedOne = expectedOne;
        this.expectedTwo = expectedTwo;
        this.result = result;
    }

    @Test
    void sum() {
        Calculator calculator = new Calculator();
        assertEquals(calculator,result);
    }

}
