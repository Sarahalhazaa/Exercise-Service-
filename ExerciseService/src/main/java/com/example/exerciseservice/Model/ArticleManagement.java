package com.example.exerciseservice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ArticleManagement {
    @NotEmpty
    private String id;

    @NotEmpty
    @Size(max = 100)
    private String title;

    @NotEmpty
    @Size(min = 5,max = 20)
    private String author;

    @NotEmpty
    @Size(min = 201)
    private String content;
    @NotEmpty
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;
    @NotEmpty
    private String imageUrl;

    private boolean isPublished=false;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate publishDate;


}
