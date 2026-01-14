package com.Blog.Blog.Repository;

import com.Blog.Blog.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcommentRepository extends JpaRepository<Comment, Long> {

    // Busca comentarios donde el id de la entidad 'posteo' coincida
    List<Comment> findByPosteo_IdPosteo(Long idPosteo);
}
