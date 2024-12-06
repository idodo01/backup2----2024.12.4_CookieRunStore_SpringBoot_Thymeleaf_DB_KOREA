package com.koreait.store.mapper;

import com.koreait.store.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    ProductDTO selectProductByNo(Integer productNo);
    
}
