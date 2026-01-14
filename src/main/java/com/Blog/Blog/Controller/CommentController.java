package com.Blog.Blog.Controller;

import com.Blog.Blog.Model.Comment;
import com.Blog.Blog.Service.IserviceComment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class CommentController {

    private final IserviceComment commentService;

    public CommentController(IserviceComment commentService) {
        this.commentService = commentService;
    }

    // 1. CREAR COMENTARIO
    @PostMapping("/crear")
    public ResponseEntity<String> crearComentario(@RequestBody Comment comentario) {
        commentService.guardarComment(comentario);
        return new ResponseEntity<>("Comentario creado con éxito", HttpStatus.CREATED);
    }

//    @PostMapping("/crear")
//    public ResponseEntity<String> crearComentario(@RequestBody Comment comentario) {
//        try {
//            commentService.guardarComment(comentario);
//            return ResponseEntity.ok("Ok");
//        } catch (Exception e) {
//            // Esto imprimirá el error real en tu consola de IntelliJ
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error real: " + e.getMessage());
//        }
//    }

    // 2. LISTAR TODOS LOS COMENTARIOS
    @GetMapping
    public List<Comment> obtenerTodos() {
        return commentService.obtenerTodos();
    }

    // 3. BUSCAR POR POSTEO ESPECÍFICO
    @GetMapping("/posteo/{idPosteo}")
    public ResponseEntity<List<Comment>> buscarPorPosteo(@PathVariable Long idPosteo) {
        List<Comment> lista = commentService.obtenerComentariosPorPosteo(idPosteo);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // 4. ELIMINAR COMENTARIO
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comentario eliminado correctamente");
    }
}