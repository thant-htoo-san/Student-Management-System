package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Student;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private int nextId = 1;

    // READ all
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return students;
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
    // CREATE
    @PostMapping()
    public Student addStudent(@RequestBody Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }

    // UPDATE
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(updatedStudent.getName());
                s.setAge(updatedStudent.getAge());
                return s;
            }
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
    }
}
