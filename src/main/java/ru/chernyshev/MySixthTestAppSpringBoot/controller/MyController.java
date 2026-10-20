package ru.chernyshev.MySixthTestAppSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.MySixthTestAppSpringBoot.entity.Student;
import ru.chernyshev.MySixthTestAppSpringBoot.model.OperationResult;
import ru.chernyshev.MySixthTestAppSpringBoot.service.StudentService;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<OperationResult> allStudents() {
        return ResponseEntity.ok(
                OperationResult.success("Список студентов получен", studentService.getAllStudents()));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<OperationResult> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return notFound("Студент с id = " + id + " не найден");
        }
        return ResponseEntity.ok(OperationResult.success("Студент найден", student));
    }

    @PostMapping("/students")
    public ResponseEntity<OperationResult> saveStudent(@RequestBody Student student) {
        try {
            return ResponseEntity.ok(
                    OperationResult.success("Студент добавлен", studentService.saveStudent(student)));
        } catch (Exception e) {
            return error("Не удалось добавить студента: " + e.getMessage());
        }
    }

    @PutMapping("/students")
    public ResponseEntity<OperationResult> updateStudent(@RequestBody Student student) {
        if (studentService.getStudent(student.getId()) == null) {
            return notFound("Студент с id = " + student.getId() + " не найден");
        }
        try {
            return ResponseEntity.ok(
                    OperationResult.success("Студент изменён", studentService.saveStudent(student)));
        } catch (Exception e) {
            return error("Не удалось изменить студента: " + e.getMessage());
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<OperationResult> deleteStudent(@PathVariable("id") int id) {
        if (studentService.getStudent(id) == null) {
            return notFound("Студент с id = " + id + " не найден");
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(OperationResult.success("Студент с id = " + id + " удалён", null));
    }

    private ResponseEntity<OperationResult> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(OperationResult.failure(message));
    }

    private ResponseEntity<OperationResult> error(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(OperationResult.failure(message));
    }
}