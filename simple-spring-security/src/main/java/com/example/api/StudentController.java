package com.example.api;

import com.example.dto.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(new Student(1, "Rohit"),
            new Student(2, "Pragati"), new Student(3, "Noopur"));

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") final Integer studentId) {
       return STUDENT_LIST.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst()
               .orElseThrow(() -> new RuntimeException("Student:" + studentId +" not found"));
    }
}
