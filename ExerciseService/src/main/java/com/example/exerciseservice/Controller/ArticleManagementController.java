package com.example.exerciseservice.Controller;

import com.example.exerciseservice.Api.ApiResponse;
import com.example.exerciseservice.Model.ArticleManagement;
import com.example.exerciseservice.service.ArticleManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/Article")
@RequiredArgsConstructor
public class ArticleManagementController {

    private final ArticleManagementService articleManagementService;

    @GetMapping("/get")
    public ResponseEntity getArticles(){
        ArrayList<ArticleManagement> articleManagements = articleManagementService.getArticle();
        return ResponseEntity.status(200).body(articleManagements);
    }

    @PostMapping("/add")
    public ResponseEntity addArticles(@RequestBody @Valid ArticleManagement articleManagement, Errors errors){
        if (errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        articleManagementService.addArticle(articleManagement);
        return ResponseEntity.status(200).body(new ApiResponse("Article added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticles(@PathVariable String id ,@RequestBody @Valid ArticleManagement articleManagement, Errors errors){
        if (errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = articleManagementService.updateArticle(id ,articleManagement);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Article updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Article not updated"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticles(@PathVariable String id){

        boolean isdeleted = articleManagementService.deleteArticle(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Article deleted"));
        }else return ResponseEntity.status(400).body(new ApiResponse("Article not deleted"));

    }


 @PutMapping("/Published/{id}")
    public ResponseEntity PublishedArticle(@PathVariable String id, @RequestBody @Valid ArticleManagement articleManagement ,Errors errors ){

     if (errors.hasErrors()){
         String message =errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
     }
        ArticleManagement A = articleManagementService.PublishArticle(id , articleManagement);
        if(A == null){
        return ResponseEntity.status(400).body(new ApiResponse("Article not Published"));}
     return ResponseEntity.status(200).body(new ApiResponse("Article Published"+A));

    }
    @GetMapping("/get-all")
    public ResponseEntity allPublishedNewsArticles(){
        ArrayList<ArticleManagement> articleManagements1 = articleManagementService.allPublishedNewsArticles();
        return ResponseEntity.status(200).body(new ApiResponse("Published News Articles: "+articleManagements1));
    }

    @GetMapping("/search/{category}")
    public ResponseEntity searchNewsArticles(@PathVariable String category){

        ArticleManagement A = articleManagementService.searchNewsArticles(category);
        if(A == null){
            return ResponseEntity.status(400).body(new ApiResponse("Article not found"));}
        else  return ResponseEntity.status(200).body(new ApiResponse("Article found"+A));

    }


}
