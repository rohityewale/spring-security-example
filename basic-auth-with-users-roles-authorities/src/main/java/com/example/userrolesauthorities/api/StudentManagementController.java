package com.example.userrolesauthorities.api;

import com.example.userrolesauthorities.dto.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(new Student(1, "Rohit"),
            new Student(2, "Pragati"), new Student(3, "Noopur"));


    @GetMapping
    public List<Student> getAllStudents(){
        return STUDENT_LIST;
    }

    @PostMapping
    public String registerStudent(@RequestBody Student student){
        return "Student registered";
    }

    @PutMapping("/{studentId}")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        return "Updated student";
    }
    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId){
        return "Deleted student";
    }
}
