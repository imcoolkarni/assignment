package com.vaibhav.assignment.service;

import com.vaibhav.assignment.core.FileOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceUnitTestConfiguration {

    @Bean
    FileOperation fileOperation(){
        return new FileOperation();
    }
    @Bean
    FileOperationServiceImpl fileOperationService() {

        return new FileOperationServiceImpl();
    }

}
