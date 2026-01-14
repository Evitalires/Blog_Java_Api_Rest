package com.Blog.Blog.Service;

import com.Blog.Blog.Model.Author;
import com.Blog.Blog.Repository.IauthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IserviceAuthor {
    private final IauthorRepository authorRepository;

    @Autowired
    public AuthorService( IauthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> obtenerTodos() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> obtenerPorId(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author guardarAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void editAuthor(Long id, Author authorActualizado) {
        // Usamos el Optional directamente para un código más limpio
        Author authorExistente = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author no encontrado con el id: " + id));

        authorExistente.setNombre(authorActualizado.getNombre());
        authorExistente.setEmail(authorActualizado.getEmail());

        //No remplando la lista para mantener consistencia
        if (authorActualizado.getPosteos() != null) {
            authorExistente.setPosteos(authorActualizado.getPosteos());
        }
        authorRepository.save(authorExistente);
    }
}
