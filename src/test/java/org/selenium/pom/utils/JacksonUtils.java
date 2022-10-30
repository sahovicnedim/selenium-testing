package org.selenium.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

//generic method koju mozemo koristit za bilo koji od nasih java objekata
public class JacksonUtils {

    public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName); //trebamo obezbjedit ime klase u kojoj se ova metoda koristi, koristi se u JacksonUtils class i zato smo pisali JacksonUtils.class
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, T);
    }

}
