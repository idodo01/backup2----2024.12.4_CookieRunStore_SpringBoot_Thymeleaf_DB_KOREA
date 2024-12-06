package com.koreait.store.service;

import com.koreait.store.dto.ProductDTO;
import com.koreait.store.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    
    public ProductDTO get_product(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }
    
    
    
    
    
}
