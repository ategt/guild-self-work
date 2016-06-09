/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;
/**
 *
 * @author apprentice
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        //configure(JsonParser.Feature..WRITE_DATES_AS_TIMESTAMPS, false);
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //.con.configure(JsonParser.Feature.ALLOW_COMMENTS, true)
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }
}