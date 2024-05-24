package com.nalas.pnccontrollers.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity()
@Table(name = "sec01_categories")
public class Category {
    @Id
    private String code;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;
}
