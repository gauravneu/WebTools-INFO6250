package com.example.onlinegamingsite.utilities;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Locale;

@AllArgsConstructor
public class Utilities {
    private final UtilityDependency utilityDependency;


    int addTwoNum(int num1,int num2){
        return num1+num2;
    }

    boolean isOrEmptyNull(String obj){
        return StringUtils.isEmpty(obj);
    }

    int addTwoNum1(int num1,int num2){
        utilityDependency.print("print numbers");
        return num1+num2;
    }
    int addTwoNum2(int num1,int num2){
        System.out.println(utilityDependency.returnString("print numbers").toLowerCase(Locale.ROOT));
        return num1+num2;
    }
}
