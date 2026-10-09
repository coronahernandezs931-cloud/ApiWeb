package com.example.cursos.controller;

import com.example.cursos.model.Curso;
import com.example.cursos.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Curso> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Optional<Curso> curso = service.findById(id);
        if (curso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso.get());
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        Curso newCurso = service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> updated = service.update(id, curso);
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
