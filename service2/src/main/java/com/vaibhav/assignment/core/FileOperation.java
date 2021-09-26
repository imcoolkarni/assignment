package com.vaibhav.assignment.core;

import com.github.opendevl.JFlat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fuzzycsv.FuzzyCSVTable;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Component
public class FileOperation {

    private static final Logger logger = LoggerFactory.getLogger(FileOperation.class);
    public String CSV_FILE = "Data.csv";
    public String CSV_FILE2 = "Data2.csv";
    public String XML_FILE = "Data.xml";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String StoreFileCSV(String data) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        logger.info("JSON Data :" + jsonObject.toString());
        JFlat jFlat = new JFlat(jsonObject.toString());
        try {
            //get the 2D representation of JSON document
            //If you want to remove the "/" from header name then use the headerSeparator()
            jFlat.json2Sheet().headerSeparator().getJsonAsSheet();
            jFlat.write2csv(CSV_FILE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error occurred while converting" + e.getMessage());
            return "Error occurred during store";
        }
        return "Store operation completed successfully";
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String UpdateFileCSV(String data) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        logger.info("JSON Data :" + jsonObject.toString());
        JFlat jFlat = new JFlat(jsonObject.toString());
        try {
            boolean filePresent = Files.exists(Path.of(CSV_FILE));
            if (!filePresent)
                return "Data does not exist please store it first";
            jFlat.json2Sheet().headerSeparator().getJsonAsSheet();
            jFlat.write2csv(CSV_FILE2);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error occurred while converting" + e.getMessage());
            return "Error occurred during update";
        }
        Reader originalReader = null;
        Reader updatedReader = null;
        try {
            File originalFile = new File(CSV_FILE);
            File updatedFile = new File(CSV_FILE2);
            originalReader = new FileReader(originalFile);
            updatedReader = new FileReader(updatedFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        FuzzyCSVTable original = FuzzyCSVTable.parseCsv(originalReader);
        FuzzyCSVTable updated = FuzzyCSVTable.parseCsv(updatedReader);
        FuzzyCSVTable output;
        try {
            output = original.mergeByColumn(updated);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred during update:" + e.getMessage());
            return "Error occurred during update";
        }

        output.write(CSV_FILE);
        return "Update operation completed successfully";
    }

    public String StoreFileXML(String data) {
        try {
            JSONObject json = new JSONObject(data);
            logger.info("JSON Data :" + json);
            String xml = XML.toString(json);
            StringBuffer stringBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            stringBuffer.append(System.lineSeparator());
            stringBuffer.append(xml);
            stringBuffer.append(System.lineSeparator());
            FileWriter fileWriter = new FileWriter(XML_FILE);
            fileWriter.write(stringBuffer.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error occurred while converting" + e.getMessage());
            return "Error occurred during store";
        }
        return "Store operation completed successfully";
    }

    public String UpdateFileXML(String data) {
        JSONObject json = new JSONObject(data);
//Plain XML entry method
        logger.info("JSON Data :" + json);
        String xml = XML.toString(json);
        //XML with format
        // Uncomment Below code for formatted XML
//        String xml = U.jsonToXml(jsonObject.toString());
        StringBuilder stringBuilder = new StringBuilder(xml);
        stringBuilder.append(System.lineSeparator());
        try {
            boolean filePresent = Files.exists(Path.of(XML_FILE));
            if (!filePresent)
                return "Data does not exist please store it first";
            FileWriter fileWriter = new FileWriter(XML_FILE, true);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred during update:" + e.getMessage());
            return "Error occurred during update";
        }
        return "Update operation completed successfully";
    }

    public String readFile(String filetype) throws IOException {
        File file = new File("Data" + "." + filetype.toLowerCase());
        FileInputStream fileInputStream = null;
//        try {
            fileInputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            logger.error("Error occurred during reading file" + e.getMessage());
//            return e.getMessage();
//        }
        String result = readFromInputStream(fileInputStream);
        if (result.equals(""))
            result = "No data present for requested file";
        return result;
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return convertString(resultStringBuilder.toString());
    }

    private String convertString(String input) {
        String string = input;
        try {
            // Convert from Unicode to UTF-8

            byte[] utf8 = string.getBytes("UTF-8");

            // Convert from UTF-8 to Unicode
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("error occurred during encoding");
        }
        return string;

    }
}
