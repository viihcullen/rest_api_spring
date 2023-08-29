package br.com.fatec.FatecProjectRestAPI.repository;

import br.com.fatec.FatecProjectRestAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
