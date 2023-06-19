package org.ivanovx.bloggable.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name="settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String name;

    @Column(nullable = false)
    private String value;
}