package br.com.fatec.FatecProjectRestAPI.service;

import br.com.fatec.FatecProjectRestAPI.entity.Product;
import br.com.fatec.FatecProjectRestAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getInfoProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        if(validateProduct(product)){
            return productRepository.saveAndFlush(product);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A renda mensal é obrigatória e deve ser maior que 0 (zero)!");
        }
    }

    public HashMap<String, Object> deleteProduct(Long idProduct){
        Optional<Product> product =
                Optional.ofNullable(productRepository.findById(idProduct).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado!")));
        productRepository.delete(product.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", "Produto: " + product.get().getNameProduct() +
                " excluido com sucesso!");
        return result;
    }

    public Optional<Product> findProductById(Long idProduct){
        return Optional.ofNullable(productRepository.findById(idProduct).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!")));
    }

    public Boolean validateProduct(Product product){
        return (product.getAmountProduct().compareTo(BigDecimal.valueOf(0)) > 0 && product.getCostPriceProduct().compareTo(BigDecimal.valueOf(0)) > 0);
    }

}
