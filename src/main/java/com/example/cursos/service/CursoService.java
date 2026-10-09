package com.example.cursos.service;

import com.example.cursos.model.Curso;
import com.example.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public List<Curso> findAll() {
        return repo.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return repo.findById(id);
    }

    public Curso save(Curso curso) {
        return repo.save(curso);
    }

    public Optional<Curso> update(Long id, Curso curso) {
        Optional<Curso> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Curso cursoBd = optional.get();
        cursoBd.setTitulo(curso.getTitulo());
        cursoBd.setDescripcion(curso.getDescripcion());
        cursoBd.setDuracionHoras(curso.getDuracionHoras());
        cursoBd.setPrecio(curso.getPrecio());
        return Optional.of(repo.save(cursoBd));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
