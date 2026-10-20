package ru.chernyshev.MySixthTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chernyshev.MySixthTestAppSpringBoot.dao.SubjectDAO;
import ru.chernyshev.MySixthTestAppSpringBoot.entity.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectDAO.findAll();
    }

    @Override
    @Transactional
    public Subject saveSubject(Subject subject) {
        return subjectDAO.save(subject);
    }

    @Override
    @Transactional
    public Subject getSubject(int id) {
        return subjectDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteSubject(int id) {
        if (!subjectDAO.existsById(id)) {
            return false;
        }
        subjectDAO.deleteById(id);
        return true;
    }
}