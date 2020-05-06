package com.example.userauthorities.api;

import com.example.userauthorities.dto.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(new Student(1, "Rohit"),
            new Student(2, "Pragati"), new Student(3, "Noopur"));


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudents(){
        return STUDENT_LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String registerStudent(@RequestBody Student student){
        return "Student registered";
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        return "Updated student";
    }
    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteStudent(@PathVariable("studentId") Integer studentId){
        return "Deleted student";
    }
}
