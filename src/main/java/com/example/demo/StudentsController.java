package com.example.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SpringApplication.run(StudentsController.class, args);
	}

	public StudentsController() {
		super();
	}

	@GetMapping("/getStudents")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			response.getWriter().println("<h1>" + DBService.getAll() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/addStudent")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String jsonstudent = HTTPUtils.getBody(request);
			DBService.addOne(jsonstudent);
			response.setContentType("text/html");
			response.getWriter().println("<h1>" + "Added Student:" + jsonstudent + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PutMapping("/updateStudent")
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String jsonstudent = HTTPUtils.getBody(request);
			DBService.updateOne(jsonstudent);
			response.setContentType("text/html");
			response.getWriter().println("<h1>" + "Updated Student:" + jsonstudent + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/deleteStudent")
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String jsonstudent = HTTPUtils.getBody(request);
			DBService.deleteOne(jsonstudent);
			response.setContentType("text/html");
			response.getWriter().println("<h1>" + "Deleted student with id:" + jsonstudent + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}