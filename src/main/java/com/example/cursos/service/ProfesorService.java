package com.example.cursos.service;

import com.example.cursos.model.Profesor;
import com.example.cursos.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository repo;

    public ProfesorService(ProfesorRepository repo) {
        this.repo = repo;
    }

    public List<Profesor> findAll() {
        return repo.findAll();
    }

    public Optional<Profesor> findById(Long id) {
        return repo.findById(id);
    }

    public Profesor save(Profesor profesor) {
        return repo.save(profesor);
    }

    public Optional<Profesor> update(Long id, Profesor profesor) {
        Optional<Profesor> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Profesor profBd = optional.get();
        profBd.setNombre(profesor.getNombre());
        profBd.setEmail(profesor.getEmail());
        profBd.setEspecialidad(profesor.getEspecialidad());
        return Optional.of(repo.save(profBd));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
