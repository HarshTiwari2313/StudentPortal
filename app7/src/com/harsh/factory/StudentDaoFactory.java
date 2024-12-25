package com.harsh.factory;

import com.harsh.dao.StudentDao;
import com.harsh.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static StudentDao studentDao;
	
       static {
    	   studentDao= new StudentDaoImpl();
       }
       
       public  static StudentDao getStudentDao() {
    	   return studentDao;
       }
}
