package com.example.demo.repositories;

import com.example.demo.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long> {
    public Article findByCodeBarreEquals(String codeBarre);
    @Query("SELECT a from Article a WHERE  ( a.codeBarre LIKE %:search% OR a.designation LIKE %:search%  OR a.description LIKE %:search% OR a.marque LIKE %:search% OR a.etat LIKE %:search%)")
    public List<Article> getArticleBySearch(@Param("search") String search);

    List<Article> findByDetailAffectation_IdEquals(Long id);
    //List<Article> findByDetailAffectation_Id(Long id);

}
