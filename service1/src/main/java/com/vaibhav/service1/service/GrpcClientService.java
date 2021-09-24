package com.vaibhav.service1.service;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.vaibhav.assignment.FileRequest;
import com.vaibhav.assignment.FileResponse;
import com.vaibhav.assignment.ReadRequest;
import com.vaibhav.assignment.Service2Grpc;
import com.vaibhav.service1.exceptions.IncorrectJsonFormatException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {
    private static Logger logger = LoggerFactory.getLogger(GrpcClientService.class);
    @GrpcClient("service1-grpc-server")
    private Service2Grpc.Service2BlockingStub service2BlockingStub;

    public String storeFile(String data, String fileType) throws IncorrectJsonFormatException {
        try {
            JsonParser.parseString(data).isJsonObject();
        } catch (JsonSyntaxException e) {
            logger.error("Error occurred during parsing request");
            throw new IncorrectJsonFormatException("500 Internal Server Error occurred", "Not a JSON Object: ", "Use valid JSON data");
        }
        try {
            final FileResponse fileResponse = this.service2BlockingStub.store(FileRequest.newBuilder().setFileData(data.trim()).setFileFormat(fileType.trim().toUpperCase()).build());
            return fileResponse.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

    public String UpdateFile(String data, String fileType)  {
        try {
            JsonParser.parseString(data).isJsonObject();
        } catch (JsonSyntaxException e) {
            logger.error("Error occurred during parsing request");
            throw new IncorrectJsonFormatException("500 Internal Server Error occurred", "Not a JSON Object: ", "Use valid JSON data");
        }
        try {
            final FileResponse fileResponse = this.service2BlockingStub.update(FileRequest.newBuilder().setFileData(data.trim()).setFileFormat(fileType.trim().toUpperCase()).build());
            return fileResponse.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

    public String readFile(String fileType) {
        if (fileType.equalsIgnoreCase("CSV") || fileType.equalsIgnoreCase("XML")) {
            try {
                final FileResponse fileResponse = this.service2BlockingStub.readFile(ReadRequest.newBuilder().setFileFormat(fileType.trim().toUpperCase()).build());
                return fileResponse.getMessage().trim();
            } catch (final StatusRuntimeException e) {
                return "FAILED with " + e.getStatus().getCode().name();
            }

        } else {
            return "Invalid fileType received";
        }
    }
}
