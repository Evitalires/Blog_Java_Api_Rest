package com.Blog.Blog.Controller;

import com.Blog.Blog.Model.Posteo;
import com.Blog.Blog.Service.PosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posteos")
public class PosteoController {

    private final PosteoService posteoService;

    @Autowired
    public PosteoController(PosteoService posteoService) {
        this.posteoService = posteoService;
    }

    @GetMapping("")
    public List<Posteo> listaPosteo() {
        return posteoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Posteo> obtenerPorId(@PathVariable Long id) {
        return posteoService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarPosteo(@RequestBody Posteo posteo) {
        posteoService.guardarPosteo((posteo));
        return ResponseEntity.ok("Posteo agregado Exitoxamente");
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editPosteo(@PathVariable Long id, @RequestBody Posteo posteo) {
        try {
            posteoService.editPosteo(id, posteo);
            return  ResponseEntity.ok("Persona actualizada con exito");
        } catch (RuntimeException e) {
            return  ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        if (posteoService.obtenerPorId(id).isPresent()) {
            posteoService.deletePosteo(id);
            return ResponseEntity.ok("Posteo eliminado con éxito");
        } else {
            return ResponseEntity.status(404).body("No se encontró el posteo con el ID: " + id);
        }
    }
}
