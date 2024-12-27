package com.actions.demo.dto;

import com.actions.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentDto {
    String name;

    public static StudentDto toDto(Student student) {
        return new StudentDto(student.getName());
    }
}
