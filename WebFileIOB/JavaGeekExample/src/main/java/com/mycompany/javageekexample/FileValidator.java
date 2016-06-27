/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javageekexample;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//import com.javacodegeeks.snippets.enterprise.fileupload.model.File;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return File.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		File file = (File) obj;
		  if (file.getFile().getSize() == 0) {
		   errors.rejectValue("file", "valid.file");
		  }
                  
                  // The Maximum File upload size is 5 GB.
                  if (file.getFile().getSize() > 5368709120L) {
		   errors.rejectValue("file", "valid.file");
		  }
	}
}