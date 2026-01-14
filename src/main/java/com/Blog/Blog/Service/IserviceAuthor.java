package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Author;

import java.util.List;
import java.util.Optional;

public interface IserviceAuthor {
    List<Author> obtenerTodos();
    Optional<Author> obtenerPorId(Long id);
    Author guardarAuthor(Author author);

    void deleteAuthor(Long id);
    void editAuthor(Long id, Author authorActualizado);
}
