package com.group.libraryapp.domain.book;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String name;

    protected Book() {}

    public Book(String name) {
        if(!StringUtils.hasText(name)){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
