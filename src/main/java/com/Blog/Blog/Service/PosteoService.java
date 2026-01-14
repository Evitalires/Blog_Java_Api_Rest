package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Author;
import com.Blog.Blog.Model.Posteo;
import com.Blog.Blog.Repository.IauthorRepository;
import com.Blog.Blog.Repository.IposteoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteoService implements IservicePosteo {
    private final IposteoRepository posteoRepository;
    private final IauthorRepository authorRepository;

    @Autowired
    public PosteoService(IposteoRepository posteoRepository, IauthorRepository authorRepository) {
        this.posteoRepository = posteoRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Posteo> obtenerTodos() {
        return posteoRepository.findAll();
    }

    @Override
    public Optional<Posteo> obtenerPorId(Long id) {
        return posteoRepository.findById(id);
    }

    @Override
    public void guardarPosteo(Posteo posteo) {
        // 1. Buscamos el autor real en la DB usando el ID que vino en el JSON
        Author autorExistente = authorRepository.findById(posteo.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        // 2. Le asignamos el autor persistido al posteo
        posteo.setAutor(autorExistente);

        // 3. Guardamos
        posteoRepository.save(posteo);
    }

    @Override
    public void deletePosteo(Long id) {
        posteoRepository.deleteById(id);
    }

    @Override
    @Transactional // Recomendado para asegurar la persistencia
    public void editPosteo(Long id, Posteo posteoActualizado) {
        Posteo posteoExistente = posteoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posteo no encontrado con el id: " + id));

        posteoExistente.setTitulo(posteoActualizado.getTitulo());
        posteoExistente.setContenido(posteoActualizado.getContenido());

        // Solo actualizamos el autor si se envió uno nuevo
        if (posteoActualizado.getAutor() != null) {
            posteoExistente.setAutor(posteoActualizado.getAutor());
        }

        posteoRepository.save(posteoExistente);
    }
}
