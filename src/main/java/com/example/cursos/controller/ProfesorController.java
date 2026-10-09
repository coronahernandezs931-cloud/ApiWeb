package com.example.cursos.controller;

import com.example.cursos.model.Profesor;
import com.example.cursos.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Profesor> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable Long id) {
        Optional<Profesor> profesor = service.findById(id);
        if (profesor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesor.get());
    }

    @PostMapping
    public ResponseEntity<Profesor> save(@RequestBody Profesor profesor) {
        Profesor newProfesor = service.save(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Long id, @RequestBody Profesor profesor) {
        Optional<Profesor> updated = service.update(id, profesor);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
