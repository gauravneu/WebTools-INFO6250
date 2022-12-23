/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment4.part9.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import javax.inject.Singleton;

/**
 *
 * @author gaurav
 */

@RequestScope
public final class Utility {
@Bean
    public String printMessage(){
        return "Printing Message in Utility Class from Part 9";
    }
}
