package com.test.mongo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.mongo.model.Student;

@Repository
public interface StudentInfo extends MongoRepository<Student, Integer> {
}
