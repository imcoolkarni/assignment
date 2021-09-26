package com.vaibhav.assignment.service;


import com.vaibhav.assignment.FileRequest;
import com.vaibhav.assignment.FileResponse;
import com.vaibhav.assignment.ReadRequest;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@SpringBootTest
@SpringJUnitConfig(classes = { MyServiceUnitTestConfiguration.class })
@RunWith(SpringRunner.class)
public class FileOperationServiceImplTest {

    public String data = "{\n" +
            "    \"name\": \"vahv\",\n" +
            "    \"dob\": \"20-08-2020\",\n" +
            "    \"salary\": \"122111241.150\",\n" +
            "    \"age\": \"25\",\n" +
            "    \"occupation\":\"Engineer\"\n" +
            "}";
    public String fileFormat = "CSV";

    public String inValidData = "{\n" +
            "    \"name\": \"vahv\",\n" +
            "    \"dob\": \"20-08-2020\",\n" +
            "    \"salary\": \"122111241.150\",\n" +
            "    \"age\": \"25\",\n" +
            "    \"occupation\":\"Engineer\"\n" +
            "}";
    public String invalidFileFormat = "CSM";

    @Autowired
    private FileOperationServiceImpl fileOperationService;

    @Test
    public void testStoreOperation() throws Exception {
        FileRequest request = FileRequest.newBuilder().setFileFormat(fileFormat).setFileData(data).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.store(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<FileResponse> response = responseObserver.getValues();
        assertEquals(1, response.size());
        FileResponse fileResponse = response.get(0);
        assertEquals(FileResponse.newBuilder()
                .setMessage("Store operation completed successfully")
                .build(), fileResponse);
    }

    //Run after store data
    @Test
    public void testsReadOperation() throws Exception {
        ReadRequest readRequest= ReadRequest.newBuilder().setFileFormat(fileFormat).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.readFile(readRequest, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<FileResponse> response = responseObserver.getValues();
        assertEquals(1, response.size());
        FileResponse fileResponse = response.get(0);
        assertEquals(FileResponse.newBuilder()
                .setMessage(data)
                .build(), fileResponse);
    }
    //Delete Data file to run this
    @Test
    public void testReadOperationFail() throws Exception {
        ReadRequest readRequest= ReadRequest.newBuilder().setFileFormat(fileFormat).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.readFile(readRequest, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        String s= null;
        StreamRecorder<FileResponse> responseObservernew = StreamRecorder.create();
        fileOperationService.readFile(readRequest, responseObservernew);
        if (!responseObservernew.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        s= responseObservernew.getError().getMessage();
        assertEquals(responseObserver.getError().getMessage(),s);
    }

    @Test
    public void testUpdateOperation() throws Exception {
        FileRequest request = FileRequest.newBuilder().setFileFormat(fileFormat).setFileData(data).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.update(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<FileResponse> response = responseObserver.getValues();
        assertEquals(1, response.size());
        FileResponse fileResponse = response.get(0);
        assertEquals(FileResponse.newBuilder()
                .setMessage("Update operation completed successfully")
                .build(), fileResponse);
    }

    @Test
    public void testStoreOperationInvalidFiletype() throws Exception {
        FileRequest request = FileRequest.newBuilder().setFileFormat(invalidFileFormat).setFileData(data).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.store(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        String s= null;
        StreamRecorder<FileResponse> responseObservernew = StreamRecorder.create();
        fileOperationService.store(request, responseObservernew);
        if (!responseObservernew.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        s= responseObservernew.getError().getMessage();
        assertEquals(responseObserver.getError().getMessage(),s);

//        assertThrows(IncorrectFileOperationException.class,respon)

    }

    @Test
    public void testStoreOperationInvalidData() throws Exception {
        FileRequest request = FileRequest.newBuilder().setFileFormat(invalidFileFormat).setFileData(data).build();
        StreamRecorder<FileResponse> responseObserver = StreamRecorder.create();
        fileOperationService.update(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        String s= null;
        StreamRecorder<FileResponse> responseObservernew = StreamRecorder.create();
        fileOperationService.update(request, responseObservernew);
        if (!responseObservernew.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        s= responseObservernew.getError().getMessage();
        assertEquals(responseObserver.getError().getMessage(),s);



    }

}
