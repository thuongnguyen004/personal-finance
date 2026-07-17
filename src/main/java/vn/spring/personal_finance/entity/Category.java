package vn.spring.personal_finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import vn.spring.personal_finance.constant.TypeEnum;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Category(String name, TypeEnum type) {
        this.name = name;
        this.type = type;
    }

    public Category(long id, String name, TypeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Category(long id, String name, TypeEnum type, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
    }
}
