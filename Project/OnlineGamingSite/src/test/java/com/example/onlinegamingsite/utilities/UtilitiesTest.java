package com.example.onlinegamingsite.utilities;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
public class UtilitiesTest {
    @Mock UtilityDependency utilityDependency;
    @InjectMocks
    Utilities utilities;

    static Stream<Arguments> paramsGenerator(){
        return Stream.of(Arguments.of(1,2,3),
                Arguments.of(2,1,3),
                Arguments.of(0,3,3),
                Arguments.of(6,4,10));
    }
    @Test
    void addTwoNumbersTest(){
        Assertions.assertEquals(3,utilities.addTwoNum(1,2));
    }
    @ParameterizedTest
    @ValueSource(ints = {2,1})
    void addTwoNumbersTest1(int i){
        System.out.println(i);
        Assertions.assertEquals(3,utilities.addTwoNum(1,2));
    }
    @ParameterizedTest
    @MethodSource("paramsGenerator")
    void addTwoNumbersTest1(int i,int j,int result){
        System.out.println(i);
        Assertions.assertEquals(result,utilities.addTwoNum(i,j));
    }
    @ParameterizedTest
    @NullAndEmptySource
    void isNullTest(String obj){
        Assertions.assertTrue(utilities.isOrEmptyNull(obj));
    }
    @Test
    void addTwoNumbersTest1(){
        Assertions.assertEquals(3,utilities.addTwoNum1(1,2));
    }
    @Test
    void addTwoNumbersTest2(){
        Mockito.when(utilityDependency.returnString(Mockito.anyString())).thenReturn("Test");
        Assertions.assertEquals(3,utilities.addTwoNum2(1,2));
    }
}
