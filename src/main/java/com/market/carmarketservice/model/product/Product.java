package com.market.carmarketservice.model.product;

import com.market.carmarketservice.model.brand.Brand;
import com.market.carmarketservice.model.category.Category;
import com.market.carmarketservice.model.info.Info;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Integer price;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Info info;
}
