package com.harsh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harsh.beans.Student;
import com.harsh.factory.StudentServiceFactory;
import com.harsh.service.StudentService;

@WebServlet(urlPatterns = {"*.do"} , loadOnStartup = 1)
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProces(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProces(request, response);
	}
	
	protected void doProces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String request_UrI = request.getRequestURI();
//		System.out.println(request_UrI);
		RequestDispatcher requestDispatcher=null;
		if(request_UrI.endsWith("addform.do")) {
			requestDispatcher = request.getRequestDispatcher("/addform.html");
			requestDispatcher.forward(request, response);
		}
		if(request_UrI.endsWith("searchform.do")) {
			requestDispatcher = request.getRequestDispatcher("/searchform.html");
			requestDispatcher.forward(request, response);
		}
		if(request_UrI.endsWith("updateform.do")) {
			requestDispatcher = request.getRequestDispatcher("/updateform.html");
			requestDispatcher.forward(request, response);
		}
		if(request_UrI.endsWith("deleteform.do")) {
			requestDispatcher = request.getRequestDispatcher("/deleteform.html");
			requestDispatcher.forward(request, response);
		}
		if(request_UrI.endsWith("add.do")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			
			StudentService studentService = StudentServiceFactory.getStudentService();
			
			String status=studentService.addStudent(student);
			
			if(status.equals("existed")) {
				requestDispatcher=request.getRequestDispatcher("existed.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("success")) {
				requestDispatcher=request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if(status.equals("failure")) {
				requestDispatcher=request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
			
		}
		
		if(request_UrI.endsWith("search.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			Student student = studentService.searchStudent(sid);
			if(student==null) {
				requestDispatcher=request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
			}
			else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body bgcolor='lightblue'>");
				out.println("<br><br><br>");
				out.println("<table align='center' border='1' >");
				out.println("<tr><td>Student Id</td><td>"+student.getSid()+"</td></tr>");
				out.println("<tr><td>Student Name</td><td>"+student.getSname()+"</td></tr>");
				out.println("<tr><td>Student Address</td><td>"+student.getSaddr()+"</td></tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				
				
			}
		}
		if(request_UrI.endsWith("editform.do")) {
			 String sid = request.getParameter("sid");
			 StudentService studentService = StudentServiceFactory.getStudentService();
			 
			 Student student = studentService.searchStudent(sid);
			 
			 if(student == null) {
				 requestDispatcher = request.getRequestDispatcher("notexisted.html");
				 requestDispatcher.forward(request, response);
			 }
			 else {
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.println("<html>");
				 out.println("<body bgcolor='lightblue'>");
				 out.println("<br><br><br>");
				 out.println("<form method='POST' action='update.do'>");
				 out.println("<table align='center' border='1' >");
				 out.println("<tr><td>Student Id</td><td>"+student.getSid()+"</td></tr>");
				 out.println("<input type='hidden' name='sid' value='"+student.getSid()+"'/>");
				 out.println("<tr> <td> Student Name </td><td><input type='text' name='sname' value='"+student.getSname()+"'/></td></tr>");
				 out.println("<tr> <td> Student Address </td><td><input type='text' name='saddr' value='"+student.getSaddr()+"'/></td></tr>");
				 out.println("<tr><td><input type='submit' value='Update'/></td></tr>");
				 out.println("</table></form>");
				 out.println("</body>");
				 out.println("</html>");
				 
			 }
		}
		if(request_UrI.endsWith("update.do")) {
			   String sid = request.getParameter("sid");
			   String sname=request.getParameter("sname");
			   String saddr = request.getParameter("saddr");
			   
			   Student student = new Student();
			   student.setSid(sid);
			   student.setSname(sname);
			   student.setSaddr(saddr);
			   
			   StudentService studentService = StudentServiceFactory.getStudentService();
			   String status=studentService.updateStudent(student);
			   if(status.equals("success")) {
				   requestDispatcher=request.getRequestDispatcher("success.html");
				   requestDispatcher.forward(request, response);
			   }
			   
			   else {
						requestDispatcher=request.getRequestDispatcher("failure.html");
						requestDispatcher.forward(request, response);
			   }
			   
		}
		if(request_UrI.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.deleteStudent(sid);
			 if(status.equals("success")) {
				   requestDispatcher=request.getRequestDispatcher("success.html");
				   requestDispatcher.forward(request, response);
			   }
			   
			 if(status.equals("failure")) {
						requestDispatcher=request.getRequestDispatcher("failure.html");
						requestDispatcher.forward(request, response);
			   }
			 if(status.equals("notexisted")) {
					requestDispatcher=request.getRequestDispatcher("notexisted.html");
					requestDispatcher.forward(request, response);
		   }
			 
		}
		
	}
	

}
