package com.vaibhav.assignment.service;


import com.vaibhav.assignment.FileRequest;
import com.vaibhav.assignment.FileResponse;
import com.vaibhav.assignment.Service2Grpc;
import com.vaibhav.assignment.core.FileOperation;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class FileOperationServiceImpl extends Service2Grpc.Service2ImplBase {

    private static final Logger logger = LoggerFactory.getLogger(FileOperationServiceImpl.class);

    @Autowired
    FileOperation fileOperation;

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
        if (result.equals(""))
            result = "Error occurred during the operation";
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

        if (result.equals(""))
            result = "Error occurred during the operation";
        FileResponse
                greetingResponse = FileResponse.newBuilder()
                .setMessage(result)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();
    }
}