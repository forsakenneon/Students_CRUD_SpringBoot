package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;

import lombok.Data;

@Data
public class DBService {
	public static List<Student> getAll() throws Exception {
		MongoCursor<Document> cursor = DBContext.fetchCollectionCursor(Database.DATA.toString(),
				Collection.STUDENTS.toString(), Student.class);
		var students = new ArrayList<Student>();
		try {
			while (cursor.hasNext()) {
				students.add(JsonUtil.fromJsontoStudent(cursor.next().toJson()));
			}
		} finally {
			cursor.close();
		}
		return students;
	}

	public static void addOne(String jsonStudent) {
		try {
			MongoCollection<Document> collection = DBContext.fetchCollection(Database.DATA.toString(),
					Collection.STUDENTS.toString(), Document.class);
			Student student = JsonUtil.fromJsontoStudent(jsonStudent);
			var document = new Document("_id", StudentUtils.generateId());

			Map<String, String> map = Map.of("firstName", student.getFirstName(), "middleName", student.getMiddleName(),
					"lastName", student.getLastName());
			document.putAll(map);
			collection.insertOne(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateOne(String jsonStudent) {
		try {
			Student student = JsonUtil.fromJsontoStudent(jsonStudent);
			String id = student.get_id();
			MongoCollection<Student> collection = DBContext.fetchCollection(Database.DATA.toString(),
					Collection.STUDENTS.toString(), Student.class);

			collection.updateOne(new Document("_id", id), Updates.set("firstName", student.getFirstName()));
			collection.updateOne(new Document("_id", id), Updates.set("middleName", student.getMiddleName()));
			collection.updateOne(new Document("_id", id), Updates.set("lastName", student.getLastName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteOne(String jsonStudent) {
		try {
			DBContext.fetchCollection(Database.DATA.toString(), Collection.STUDENTS.toString(), Student.class)
					.deleteOne(new Document("_id", JsonUtil.fromJsontoStudent(jsonStudent).get_id()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}