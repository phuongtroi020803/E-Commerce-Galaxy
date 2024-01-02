package com.example.ecommercegalaxy.entity;

import com.example.ecommercegalaxy.constance.ConstraintName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category",
        uniqueConstraints = {
                @UniqueConstraint(name = ConstraintName.CATEGORY_NAME_UNIQUE, columnNames = "name")
        }
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "level", nullable = true)
    private Integer level;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Nationalized
    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(mappedBy = "category")
    private List<CategoryAttribute> categoryAttributes = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();


}