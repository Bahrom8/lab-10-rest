package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.StudentDTO;
import com.cydeo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

     /*
           Endpoint: /api/v1/student
           HTTP Status Code: 200

           JSON Response Body:
           "success": true
           "message": "Students are successfully retrieved."
           "code":200
           "data":<students data>
     */

    @GetMapping
    public ResponseEntity<ResponseWrapper> readAllStudents(){
        //get all the students
        List<StudentDTO> students = studentService.findAll();
        //create response wrapper
        ResponseWrapper responseWrapper = ResponseWrapper.builder()
                .success(true).code(HttpStatus.OK.value())
                .message("Students are successfully retrieved.")
                .data(students).build();
        //return them with status code:200
        return ResponseEntity.ok(responseWrapper);

        //one statement
//        return ResponseEntity.ok(ResponseWrapper.builder()
//                .success(true).code(HttpStatus.OK.value())
//                .message("Students are successfully retrieved.")
//                .data(studentService.findAll()).build());
    }

    /*
          Endpoint: /api/v1/student
          HTTP Status Code: 201

          JSON Response Body:
          "success": true
          "message": "Student is successfully created."
          "code":201
          "data":<created student data>
    */
    @PostMapping
    public ResponseEntity<ResponseWrapper> createStudent(@RequestBody StudentDTO studentDTO){
        //create the student
        StudentDTO createdStudent = studentService.createStudent(studentDTO);

        //response wrapper
        ResponseWrapper responseWrapper = ResponseWrapper.builder()
                .success(true).code(HttpStatus.CREATED.value())
                .message("Student is successfully created.")
                .data(createdStudent).build();

        //return json with 201
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseWrapper);
    }

}
