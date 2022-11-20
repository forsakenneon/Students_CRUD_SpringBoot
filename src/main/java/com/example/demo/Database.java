package com.example.demo;

public enum Database {
	DATA("data");

	private String dbName;

	private Database(String name) {
		this.dbName = name;
	}

	@Override
	public String toString() {
		return dbName;
	}
}