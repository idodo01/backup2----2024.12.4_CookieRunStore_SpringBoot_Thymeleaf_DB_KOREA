package com.koreait.store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Integer no;
    private Integer price;
    private String name;
    private String detail;
    private LocalDateTime uploadedAt;
    private List<ProductOptionDTO> options;
    private List<ProductImageDTO> images;
}











