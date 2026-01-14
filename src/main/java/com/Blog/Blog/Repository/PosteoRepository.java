//package com.Blog.Blog.Repository;
//
//import com.Blog.Blog.Model.Posteo;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PosteoRepository implements IposteoRepository{
//    private final List<Posteo> posteos = new ArrayList<>();
//
//    public PosteoRepository() {
//      posteos.add(new Posteo(1L, "Como entregarse a la tranquilidad", "Jose Cielos"));
//      posteos.add(new Posteo(2L, "Angeles o Demonios", "Lucifer Rodriguez"));
//    }
//
//    @Override
//    public List<Posteo> findAll() {
//        return posteos;
//    }
//
//    @Override
//    public Posteo findById(Long id) {
//        for (Posteo posteo : posteos) {
//            if(posteo.getIdPosteo().equals((id))) {
//                return posteo;
//            }
//        }
//        return  null;
//    }
//
//    @Override
//    public void save(Posteo posteo) {
//        posteos.add(posteo);
//    }
//}
