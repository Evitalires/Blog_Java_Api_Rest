package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Posteo;

import java.util.List;
import java.util.Optional;

public interface IservicePosteo {
    List<Posteo> obtenerTodos();
    Optional <Posteo> obtenerPorId(Long id);
    void guardarPosteo(Posteo posteo);

    void deletePosteo(Long id);
    void editPosteo(Long id, Posteo posteoActualizado);
}
