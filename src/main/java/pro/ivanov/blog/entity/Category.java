package pro.ivanov.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany
    private Set<Article> articles;
}
