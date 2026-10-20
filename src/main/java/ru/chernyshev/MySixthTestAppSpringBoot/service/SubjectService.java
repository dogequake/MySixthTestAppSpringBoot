package ru.chernyshev.MySixthTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.chernyshev.MySixthTestAppSpringBoot.entity.Subject;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> getAllSubjects();

    Subject saveSubject(Subject subject);

    Subject getSubject(int id);

    boolean deleteSubject(int id);
}