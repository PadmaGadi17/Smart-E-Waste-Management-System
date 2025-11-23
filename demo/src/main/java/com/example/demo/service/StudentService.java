package com.example.demo.service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // ADD student
    public StudentEntity addStudent(StudentEntity student) {
        return repository.save(student);
    }

    // GET all students
    public List<StudentEntity> getAllStudents() {
        return repository.findAll();
    }

    // GET student by ID
    public StudentEntity getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE student
    public StudentEntity updateStudent(StudentEntity student) {
        return repository.save(student);
    }

    // DELETE student
    public String deleteStudent(Long id) {
        repository.deleteById(id);
        return "Student deleted with ID: " + id;
    }
}



