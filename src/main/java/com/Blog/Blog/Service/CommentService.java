package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Comment;
import com.Blog.Blog.Repository.IcommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements IserviceComment {

    private final IcommentRepository commentRepository;

    @Autowired
    public CommentService(IcommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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