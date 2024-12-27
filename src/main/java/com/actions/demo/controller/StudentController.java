package com.actions.demo.controller;

import com.actions.demo.dto.StudentDto;
import com.actions.demo.entity.Student;
import com.actions.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> students = studentRepository.findAll().stream()
                .map(StudentDto::toDto)
                .collect(Collectors.toList());


        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        StudentDto studentDto = StudentDto.toDto(student);

        return ResponseEntity.ok(studentDto);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {

        studentRepository.save(Student.toEntity(studentDto));
        return ResponseEntity.ok(studentDto);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDto> modifyStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElseThrow();

        if (student != null) {
            student.setName("Sally");
        }

        return ResponseEntity.ok(StudentDto.toDto(student));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElseThrow();

        if (student != null) {
            studentRepository.deleteById(id);
        }

        return ResponseEntity.ok(StudentDto.toDto(student));
    }

}

