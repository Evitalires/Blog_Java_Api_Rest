package com.Blog.Blog.Repository;

import com.Blog.Blog.Model.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//
//public interface IposteoRepository {
//    List<Posteo> findAll();
//    Posteo findById(Long id);
//    void save(Posteo posteo);
//}

@Repository
public interface IposteoRepository  extends JpaRepository<Posteo, Long> {
}