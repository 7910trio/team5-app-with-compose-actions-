package com.actions.demo.entity;

import com.actions.demo.dto.StudentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    Long id;

    @Column(name = "s_name")
    String name;

    public static Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        return student;
    }
}

