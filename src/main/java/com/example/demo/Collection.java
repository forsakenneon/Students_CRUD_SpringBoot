package com.example.demo;

public enum Collection {
	STUDENTS("Students");

	private String collName;

	private Collection(String name) {
		this.collName = name;
	}

	@Override
	public String toString() {
		return collName;
	}
}