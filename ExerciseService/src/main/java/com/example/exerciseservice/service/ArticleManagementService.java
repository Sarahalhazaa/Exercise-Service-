package com.example.exerciseservice.service;

import com.example.exerciseservice.Model.ArticleManagement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ArticleManagementService {
    ArrayList<ArticleManagement> articleManagements = new ArrayList<>();
    ArrayList<ArticleManagement> PublishedArticle = new ArrayList<>();

    public ArrayList<ArticleManagement> getArticle() {
        return articleManagements;
    }
    public void addArticle(ArticleManagement articleManagement ){

        articleManagements.add(articleManagement);
    }

    public boolean updateArticle(String id , ArticleManagement articleManagement ){
        for (int i =0 ; i <articleManagements.size();i++){
            if (articleManagements.get(i).getId().equalsIgnoreCase(id)){
               articleManagements.set(i,articleManagement);
                return true;
            }
        }
        return false;
    }
    public boolean deleteArticle(String id){
        for (int i =0 ; i <articleManagements.size();i++){
            if (articleManagements.get(i).getId().equalsIgnoreCase(id)){
                articleManagements.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArticleManagement PublishArticle(String id , ArticleManagement articleManagement){
        for (int i =0 ; i <articleManagements.size();i++) {
            if (articleManagements.get(i).getId().equalsIgnoreCase(id)) {
                PublishedArticle.add(articleManagements.get(i));
                articleManagements.set(i,articleManagement);
                articleManagements.get(i).setPublished(true);
            return  articleManagements.get(i);
            }
        }
        return null;
    }
    public ArrayList<ArticleManagement> allPublishedNewsArticles() {
        return PublishedArticle;
    }

    public ArticleManagement searchNewsArticles(String category){
        for (int i =0 ; i <articleManagements.size();i++){
            if (articleManagements.get(i).getCategory().equalsIgnoreCase(category)){
                return articleManagements.get(i);
            }
        }
        return  null;
    }

}
