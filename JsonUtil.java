package com.railway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void save(Object data, String fileName) {

        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(fileName), data);

            System.out.println(
                    "Data saved successfully to " + fileName
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving data: " + e.getMessage()
            );
        }
    }

    public static <T> T load(String fileName, Class<T> classType) {

        try {
            return mapper.readValue(
                    new File(fileName),
                    classType
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while loading data: " + e.getMessage()
            );

            return null;
        }
    }
}