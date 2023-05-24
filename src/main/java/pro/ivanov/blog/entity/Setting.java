package pro.ivanov.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String value;

    public Setting() { }

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }
}