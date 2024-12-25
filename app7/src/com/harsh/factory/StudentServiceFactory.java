package com.harsh.factory;

import com.harsh.service.StudentService;
import com.harsh.service.StudentServiceImpl;

public class StudentServiceFactory {
     private static StudentService studentService;
     static {
    	 studentService = new StudentServiceImpl();
    	 
     }
     public static StudentService getStudentService() {
    	 return studentService;
     }
}
