package com.example.ecommercegalaxy.entity;

import com.example.ecommercegalaxy.constance.ConstraintName;
import com.example.ecommercegalaxy.constance.DefaultValue;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classtify_group",
        uniqueConstraints = {
                @UniqueConstraint(name = ConstraintName.CLASSIFY_GROUP_NAME_UNIQUE, columnNames = {"value", "product_id"})
        }
)
public class ClassifyGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Nationalized
    @Column(name = "value")
    private String value = DefaultValue.SPECIAL_VALUE; // Nó là value của ClassifyGroup

    private String image; // Tạm thời bỏ qua

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Classify> classifies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "classify_name_id")
    private ClassifyName classifyName;


    public Long getQuantitySold() {
        return this.getClassifies().stream().mapToLong(Classify::getQuantitySold).sum();
    }

    public String getName() {
        return this.getClassifyName() == null ? null : this.getClassifyName().getName();
    }
}
