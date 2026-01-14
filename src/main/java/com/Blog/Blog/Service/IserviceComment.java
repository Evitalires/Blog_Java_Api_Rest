package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Author;
import com.Blog.Blog.Model.Comment;

import java.util.List;
import java.util.Optional;

public interface IserviceComment {
    List<Comment> obtenerTodos();
    Optional<Comment> obtenerPorId(Long id);
    Comment guardarComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> obtenerComentariosPorPosteo(Long idPosteo);

    void editComment(Long id, Comment commentActualizado);
}
