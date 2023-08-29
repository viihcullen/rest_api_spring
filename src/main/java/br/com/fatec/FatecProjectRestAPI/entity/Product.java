package br.com.fatec.FatecProjectRestAPI.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_product", unique = true, nullable = false, length = 300)
    private String nameProduct;

    @Column(name = "description_product", nullable = false, length = 3000)
    private String descriptionProduct;

    @Column(name = "sku_product", nullable = false, length = 10)
    private String skuProduct;

    @Column(name = "ean_product", nullable = false, length = 15)
    private String eanProduct;

    @Column(name = "cost_price_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPriceProduct;

    @Column(name = "amount_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountProduct;

    @Column(name = "published_product", nullable = false)
    private Boolean publishedProduct;

    @Column(name = "date_created_product", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedProduct;

    @PrePersist
    private void prePersist() {
        this.setDateCreatedProduct(LocalDate.now());
        this.setPublishedProduct(false);
    }

}