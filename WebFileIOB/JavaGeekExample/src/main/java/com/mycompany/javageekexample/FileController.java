/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javageekexample;

import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fileControl")
public class FileController {

    @Autowired
    FileValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(method = RequestMethod.GET, value="/")
    public String getForm(Model model) {
        java.io.File fileImagesDir = new java.io.File("./uploadedImages");
        if (!fileImagesDir.exists()) {
            fileImagesDir.mkdir();
        }

        java.io.File[] images = fileImagesDir.listFiles(new FileFilter() {

            private final String[] okFileExtensions
                    = new String[]{"jpg", "jpeg", "png", "gif"};

            public boolean accept(java.io.File pathname) {
                for (String extension : okFileExtensions) {
                    if (pathname.getName().toLowerCase().endsWith(extension)) {
                        return true;
                    }
                }
                return false;
            }

        });

        //List<java.io.File> imageList = new ArrayList();
        //images
        model.addAttribute("images", images);

        File imageFolder = new File();

//        File rootFolder = new File(Application.ROOT);
//		List<String> fileNames = Arrays.stream(rootFolder.listFiles())
//			.map(f -> f.getName())
//			.collect(Collectors.toList());
//
//		model.addAttribute("files",
//			Arrays.stream(rootFolder.listFiles())
//					.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
//					.map(f -> f.getName())
//					.collect(Collectors.toList())
//		);
        return "fileView";
    }

    @RequestMapping(method = RequestMethod.POST, value="/")
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

                java.io.File fileImagesDir = new java.io.File("./uploadedImages");
                if (!fileImagesDir.exists()) {
                    fileImagesDir.mkdir();
                }

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

                java.io.File saveFile = null;
                if (fileImagesDir.isDirectory()) {
                    saveFile = new java.io.File(fileImagesDir.getAbsolutePath() + "/" + originalName);
                }

                InputStream secondInputStream = multipartFile.getInputStream();
                IOUtils.copy(secondInputStream, new FileOutputStream(saveFile));
                String savedPath = saveFile.getAbsolutePath();
                model.addAttribute("savedPath", savedPath);


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

    @RequestMapping(value = "/upload/", method = RequestMethod.GET)
    public String ajaxFilePreUploaded(Model model){
        return "fileDrop";
    }
    
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Boolean ajaxFileUploaded(Model model, @Validated File file,
            BindingResult result) {

        String filePath = "";

        //String returnVal = "successFile";
        if (result.hasErrors()) {

            //returnVal = "fileView";
            return false;
        } else {
            InputStream inputStream = null;
            try {
                MultipartFile multipartFile = file.getFile();
                //multipartFile.
                // IOUtils.copy(byteArrayInputStream, new FileOutputStream(outputFileName));

                inputStream = multipartFile.getInputStream();

                java.io.File fileImagesDir = new java.io.File("./uploadedImages");
                if (!fileImagesDir.exists()) {
                    fileImagesDir.mkdir();
                }

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

                java.io.File saveFile = null;
                if (fileImagesDir.isDirectory()) {
                    saveFile = new java.io.File(fileImagesDir.getAbsolutePath() + "/" + originalName);
                }

                InputStream secondInputStream = multipartFile.getInputStream();
                IOUtils.copy(secondInputStream, new FileOutputStream(saveFile));
                String savedPath = saveFile.getAbsolutePath();
                model.addAttribute("savedPath", savedPath);


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
        return true;
       // return returnVal;
    }
}
