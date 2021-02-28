package com.test.mongo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.test.mongo.model.Student;

@Repository("StudentInfoImpl")
public class StudentInfoImpl implements StudentInfo {
	
	@Autowired
	private MongoClient mongoClient;

	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		MongoDatabase mdb = mongoClient.getDatabase("student_info");
		MongoCollection<Document> mcl = mdb.getCollection("student");
		FindIterable<Document> docs = mcl.find();
	
		if(docs !=null) {
			MongoCursor<Document> m = docs.iterator();
			while(m.hasNext()){
				Document doc = (Document) m.next();
				students.add(new Student(doc.getInteger("_id"),doc.getString("name"),doc.getString("department"),null));
			}
		}
		
		return students;
	}

	@Override
	public Long updateStudent(Student s) {
		MongoDatabase mdb = mongoClient.getDatabase("student_info");
		MongoCollection<Document> mcl = mdb.getCollection("student");
	
		
		UpdateResult res = mcl.updateOne(Filters.eq("_id", s.getId()),
				Updates.combine(Updates.set("name", s.getName()),Updates.set("department", s.getDepartment()))
				);
		
		
		return res.getModifiedCount();
	}

	@Override
	public Optional<Student> getStudentById(int id) {
		Student student = null;
		MongoDatabase mdb = mongoClient.getDatabase("student_info");
		MongoCollection<Document> mcl = mdb.getCollection("student");
		FindIterable<Document> docs = mcl.find(Filters.eq("_id",id));
		
		if(docs !=null) {
			MongoCursor<Document> m = docs.iterator();
			if(m.hasNext()){
				Document doc = (Document) m.next();
				student= new Student(doc.getInteger("_id"),doc.getString("name"),doc.getString("department"),null);
			}
		}
		
		return Optional.ofNullable(student);
	}

	@Override
	public String insertStudent(Student s) {
		MongoDatabase mdb = mongoClient.getDatabase("student_info");
		MongoCollection<Document> mcl = mdb.getCollection("student");
		
		Document doc = new Document();
		doc.append("_id", s.getId());
		doc.append("name", s.getName());
		doc.append("department", s.getDepartment());
		
		InsertOneResult res = mcl.insertOne(doc);
				
		return res.toString();
	}

	@Override
	public Long removStudentById(int id) {
		MongoDatabase mdb = mongoClient.getDatabase("student_info");
		MongoCollection<Document> mcl = mdb.getCollection("student");
	
		DeleteResult res = mcl.deleteOne(Filters.eq("_id",id));
				
		return res.getDeletedCount();		
	}

}
