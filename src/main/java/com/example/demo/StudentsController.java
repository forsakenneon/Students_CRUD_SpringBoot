package com.example.demo;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StudentsController extends HttpServlet {
	public static void main(String[] args) {
		SpringApplication.run(StudentsController.class, args);
	}
}