package com.vaibhav.service1.controllers;

import com.vaibhav.service1.service.GrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Service1controller {

    private static Logger logger = LoggerFactory.getLogger(Service1controller.class);
    @Autowired
    private GrpcClientService grpcClientService;

    @PostMapping("/store")
    public String storeFile(@RequestParam("fileType") String fileType, @RequestBody String data) {
        return grpcClientService.storeFile(data, fileType);
    }

    @PostMapping("/update")
    public String updateFile(@RequestParam("fileType") String fileType, @RequestBody String data) {
        return grpcClientService.UpdateFile(data, fileType);

    }

    @GetMapping("/read")
    public String readFile(@RequestParam("fileType") String fileType) {
        return grpcClientService.readFile(fileType);
    }
}
