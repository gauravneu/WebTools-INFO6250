/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment4.part8.utility;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author gaurav
 */

@Configuration
public class Utility {
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    @Bean
    public String printMessage() {
        return "Printing Message in Utility Class";
    }
}
