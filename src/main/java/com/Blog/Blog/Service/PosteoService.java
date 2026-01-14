package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Posteo;
import com.Blog.Blog.Repository.IposteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteoService implements IservicePosteo {
    private final IposteoRepository posteoRepository;

    @Autowired
    public PosteoService(IposteoRepository posteoRepository) {
        this.posteoRepository = posteoRepository;
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
        posteoRepository.save(posteo);
    }

    @Override
    public void deletePosteo(Long id) {
        posteoRepository.deleteById(id);
    }

    @Override
    public void editPosteo(Long id, Posteo posteoActualizado) {
        Posteo posteoExistente = posteoRepository.findById(id).orElse(null);
        if(posteoExistente != null) {
            //Actualizo
            posteoExistente.setAutor(posteoActualizado.getAutor());
            posteoExistente.setTitulo((posteoActualizado.getTitulo()));
            posteoExistente.setContenido(posteoActualizado.getContenido());
            //Guardo
            posteoRepository.save((posteoExistente));
        } else {
            throw  new RuntimeException("Posteo no encontrado con el id: " + id);
        }
    }
}
