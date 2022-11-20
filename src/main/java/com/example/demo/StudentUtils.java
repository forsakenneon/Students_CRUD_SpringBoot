package com.example.demo;

import java.util.UUID;

import lombok.Data;

@Data
public class StudentUtils {
	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}