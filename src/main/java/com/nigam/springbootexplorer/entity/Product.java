package com.nigam.springbootexplorer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique", columnNames = {"title", "price"})
        },
        indexes = {
                @Index(name = "sku_index", columnList = "sku")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", length = 20, nullable = false)
    private String sku;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "price", length = 20, nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", length = 20, nullable = false)
    private Integer quantity;

    @Column(name = "create_date")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "update_date")
    @LastModifiedDate
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

}
