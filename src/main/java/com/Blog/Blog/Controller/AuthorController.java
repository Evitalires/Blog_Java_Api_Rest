package com.Blog.Blog.Controller;

import com.Blog.Blog.Model.Author;
import com.Blog.Blog.Service.IserviceAuthor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AuthorController {

    private final IserviceAuthor autorService;

    public AuthorController(IserviceAuthor autorService) {
        this.autorService = autorService;
    }

    // Usamos ResponseEntity para devolver códigos de estado HTTP correctos (201 Created)
    @PostMapping
    public ResponseEntity<String> crearAuthor(@RequestBody Author author) {
        autorService.guardarAuthor(author);
        return new ResponseEntity<>("Autor creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Author> obtenerTodos() {
        return autorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarAuthor(@PathVariable Long id, @RequestBody Author author) {
        autorService.editAuthor(id, author);
        return ResponseEntity.ok("Autor actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAuthor(@PathVariable Long id) {
        autorService.deleteAuthor(id);
        return ResponseEntity.ok("Autor eliminado correctamente");
    }
}