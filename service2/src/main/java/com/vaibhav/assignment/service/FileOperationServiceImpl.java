package com.vaibhav.assignment.service;


import com.vaibhav.assignment.FileRequest;
import com.vaibhav.assignment.FileResponse;
import com.vaibhav.assignment.ReadRequest;
import com.vaibhav.assignment.Service2Grpc;
import com.vaibhav.assignment.core.FileOperation;
import com.vaibhav.assignment.exception.IncorrectFileOperationException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.IncompatibleConfigurationException;

import java.io.FileNotFoundException;
import java.io.IOException;

@GrpcService
public class FileOperationServiceImpl extends Service2Grpc.Service2ImplBase {

    private static final Logger logger = LoggerFactory.getLogger(FileOperationServiceImpl.class);

    @Autowired
    FileOperation fileOperation;

    public FileOperationServiceImpl() {
    }

    @Override
    public void store(FileRequest request, StreamObserver<FileResponse> responseObserver) {
        String data = request.getFileData();
        String fileType = request.getFileFormat();
        logger.debug("File Data:" + data);
        logger.debug("File format:" + fileType);
        String result = "";
        if (fileType.equals("CSV")) {
            result = fileOperation.StoreFileCSV(data);
        } else if (fileType.equals("XML")) {
            result = fileOperation.StoreFileXML(data);
        }
        if (result.equals("")) {
            result = "Error occurred during the operation";
            responseObserver.onError(new IncorrectFileOperationException("Incorrect file operation"));
        }
        FileResponse
                greetingResponse = FileResponse.newBuilder()
                .setMessage(result)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void update(FileRequest request, StreamObserver<FileResponse> responseObserver) {
//        super.update(request, responseObserver)
        String data = request.getFileData();
        String fileType = request.getFileFormat();
        logger.debug("File Data:" + data);
        logger.debug("File format:" + fileType);
        String result = "";
        if (fileType.equals("CSV")) {
            result = fileOperation.UpdateFileCSV(data);
        } else if (fileType.equals("XML")) {
            result = fileOperation.UpdateFileXML(data);
        }

        if (result.equals("")) {
            result = "Error occurred during the operation";
            responseObserver.onError(new IncompatibleConfigurationException("Incorrect file operation"));
        }
        FileResponse
                greetingResponse = FileResponse.newBuilder()
                .setMessage(result)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void readFile(ReadRequest request, StreamObserver<FileResponse> responseObserver) {
        String Filetype = request.getFileFormat();
        String result = null;
        try {
            result = fileOperation.readFile(Filetype);
        } catch (IOException e) {
            responseObserver.onError(new FileNotFoundException());
            logger.error("Error occurred: ", e.getMessage());
        }
        if(result==null){
            result="result not found";
        }
        FileResponse
                greetingResponse = FileResponse.newBuilder()
                .setMessage(result)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();
    }
}