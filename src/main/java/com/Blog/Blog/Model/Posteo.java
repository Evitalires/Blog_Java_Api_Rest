package com.Blog.Blog.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posteos")
public class Posteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosteo;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    private String autor;

    // Constructor vacío (Obligatorio para JPA)
    public Posteo() {
    }

    public Posteo(Long idPosteo, String titulo, String contenido, LocalDateTime fechaCreacion, String autor) {
        this.idPosteo = idPosteo;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
    }
    //Automatizar creacion de fecha
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getIdPosteo() {
        return idPosteo;
    }

    public void setIdPosteo(Long idPosteo) {
        this.idPosteo = idPosteo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
