/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
public class JodaDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {

    private static final String dateFormat = ("MM/dd/yyyy");

    @Override
    public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
    
      String formattedDate = date.format(DateTimeFormatter.ofPattern(dateFormat));
        
    //String formattedDate = LocalDateTimeFormat.forPattern(dateFormat).print(date);
    //String formattedDate = 
      //      LocalDateTimeFormat.forPattern(dateFormat).print(date);
    
    gen.writeString(formattedDate);
    }
    
    /* @Override
    public void serialize(DateTime t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */

}