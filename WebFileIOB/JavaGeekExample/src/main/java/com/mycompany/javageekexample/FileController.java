/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javageekexample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

//import com.javacodegeeks.snippets.enterprise.fileupload.model.File;
//import com.javacodegeeks.snippets.enterprise.fileupload.validator.FileValidator;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/file.htm")
public class FileController {

    @Autowired
    FileValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model) {
        File fileModel = new File();
        model.addAttribute("file", fileModel);
        return "fileView";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fileUploaded(Model model, @Validated File file,
            BindingResult result) {

        String filePath = "";

        String returnVal = "successFile";
        if (result.hasErrors()) {
            returnVal = "fileView";
        } else {
            InputStream inputStream = null;
            try {
                MultipartFile multipartFile = file.getFile();
                //multipartFile.
                // IOUtils.copy(byteArrayInputStream, new FileOutputStream(outputFileName));

                inputStream = multipartFile.getInputStream();
                java.io.File outputFile = new java.io.File("test.file");
                filePath = outputFile.getAbsolutePath();
                OutputStream outputStream = new FileOutputStream(outputFile);
                IOUtils.copy(inputStream, outputStream);
                model.addAttribute("filePath", filePath);

                String originalName = multipartFile.getOriginalFilename();
                String contentType = multipartFile.getContentType();
                Long fileSize = multipartFile.getSize();
                String multipartFileName = multipartFile.getName();
                
                model.addAttribute("originalName", originalName);
                model.addAttribute("contentType", contentType);
                model.addAttribute("fileSize", fileSize);
                model.addAttribute("multipartFileName", multipartFileName);

                
                
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return returnVal;
    }
}
