package ru.chernyshev.MySixthTestAppSpringBoot.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "hours")
    private Integer hours;
}