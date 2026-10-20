package ru.chernyshev.MySixthTestAppSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.MySixthTestAppSpringBoot.entity.Subject;
import ru.chernyshev.MySixthTestAppSpringBoot.model.OperationResult;
import ru.chernyshev.MySixthTestAppSpringBoot.service.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public ResponseEntity<OperationResult> allSubjects() {
        return ResponseEntity.ok(
                OperationResult.success("Список дисциплин получен", subjectService.getAllSubjects()));
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<OperationResult> getSubject(@PathVariable("id") int id) {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return notFound("Дисциплина с id = " + id + " не найдена");
        }
        return ResponseEntity.ok(OperationResult.success("Дисциплина найдена", subject));
    }

    @PostMapping("/subjects")
    public ResponseEntity<OperationResult> saveSubject(@RequestBody Subject subject) {
        try {
            return ResponseEntity.ok(
                    OperationResult.success("Дисциплина добавлена", subjectService.saveSubject(subject)));
        } catch (Exception e) {
            return error("Не удалось добавить дисциплину: " + e.getMessage());
        }
    }

    @PutMapping("/subjects")
    public ResponseEntity<OperationResult> updateSubject(@RequestBody Subject subject) {
        if (subjectService.getSubject(subject.getId()) == null) {
            return notFound("Дисциплина с id = " + subject.getId() + " не найдена");
        }
        try {
            return ResponseEntity.ok(
                    OperationResult.success("Дисциплина изменена", subjectService.saveSubject(subject)));
        } catch (Exception e) {
            return error("Не удалось изменить дисциплину: " + e.getMessage());
        }
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<OperationResult> deleteSubject(@PathVariable("id") int id) {
        if (!subjectService.deleteSubject(id)) {
            return notFound("Дисциплина с id = " + id + " не найдена");
        }
        return ResponseEntity.ok(OperationResult.success("Дисциплина с id = " + id + " удалена", null));
    }

    private ResponseEntity<OperationResult> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(OperationResult.failure(message));
    }

    private ResponseEntity<OperationResult> error(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(OperationResult.failure(message));
    }
}