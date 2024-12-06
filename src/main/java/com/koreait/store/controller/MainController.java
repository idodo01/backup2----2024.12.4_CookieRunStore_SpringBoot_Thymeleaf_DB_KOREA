package com.koreait.store.controller;

import com.koreait.store.dto.ProductDTO;
import com.koreait.store.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class MainController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/product/{productNo}")
    public String get_product(
            @PathVariable Integer productNo, 
            Model model
    ) {
        ProductDTO product = productService.get_product(productNo);
        model.addAttribute("product", product);
        log.warn(product);
        return "main/product";
    }
}
