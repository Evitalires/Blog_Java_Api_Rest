package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Comment;
import com.Blog.Blog.Model.Posteo;
import com.Blog.Blog.Repository.IcommentRepository;
import com.Blog.Blog.Repository.IposteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements IserviceComment {

    private final IcommentRepository commentRepository;
    private final IposteoRepository posteoRepository;

    @Autowired
    public CommentService(IcommentRepository commentRepository, IposteoRepository posteoRepository) {
        this.commentRepository = commentRepository;
        this.posteoRepository = posteoRepository;
    }

    @Override
    public List<Comment> obtenerTodos() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> obtenerPorId(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment guardarComment(Comment comment) {
        // Buscamos el posteo real por ID para asegurar la relación
        Posteo posteoExistente = posteoRepository.findById(comment.getPosteo().getIdPosteo())
                .orElseThrow(() -> new RuntimeException("El posteo ID " + comment.getPosteo().getIdPosteo() + " no existe"));

        comment.setPosteo(posteoExistente);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> obtenerComentariosPorPosteo(Long idPosteo) {
        return commentRepository.findByPosteo_IdPosteo(idPosteo);
    }

    @Override
    @Transactional
    public void editComment(Long id, Comment commentActualizado) {
        Comment commentExistente = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con id: " + id));

        // Actualizamos solo los campos permitidos
        commentExistente.setNombre(commentActualizado.getNombre());
        commentExistente.setComentario(commentActualizado.getComentario());

        // Mantener la relación con el posteo si es necesario
        if (commentActualizado.getPosteo() != null) {
            commentExistente.setPosteo(commentActualizado.getPosteo());
        }

        commentRepository.save(commentExistente);
    }
}