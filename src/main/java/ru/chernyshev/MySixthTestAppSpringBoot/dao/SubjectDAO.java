package ru.chernyshev.MySixthTestAppSpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chernyshev.MySixthTestAppSpringBoot.entity.Subject;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
}