package com.vaibhav.service1.controller;

import com.vaibhav.service1.controllers.Service1controller;
import com.vaibhav.service1.service.GrpcClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Service1ControllerTest {
    public String data = "{\n" +
            "    \"name\": \"vahv\",\n" +
            "    \"dob\": \"20-08-2020\",\n" +
            "    \"salary\": \"122111241.150\",\n" +
            "    \"age\": \"25\",\n" +
            "    \"occupation\":\"Engineer\"\n" +
            "}";
    public String fileFormat = "CSV";
    @Mock
    private GrpcClientService grpcClientService;

    @InjectMocks
    private Service1controller service1controller;

    @BeforeEach
    void setMockOutput() {
        lenient().when(grpcClientService.storeFile(data, fileFormat)).thenReturn("Store operation completed successfully");
    }

    @Test
    public void shouldReturnDefaultMessage() {
        String response = service1controller.storeFile(data, fileFormat);
        assertThat(response).isEqualTo("Store operation completed successfully");
    }
}

