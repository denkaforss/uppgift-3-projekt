package me.code.uppgift3projekt.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Post {

    private String title;
    private String content;
    private User creator;
    private LocalDate createdAt;

}
