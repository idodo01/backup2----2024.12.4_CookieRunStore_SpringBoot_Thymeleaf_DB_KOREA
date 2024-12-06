package com.koreait.store.controller;

import com.koreait.store.dto.FileDTO;
import com.koreait.store.mapper.FileMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController {
    @Autowired private FileMapper fileMapper;
    
    @GetMapping("/image/{imageNo}")
    public ResponseEntity<byte[]> get_product_image(
            @PathVariable Integer imageNo
    ) {
        FileDTO fileDTO = fileMapper.getImageFileByNo(imageNo);
        byte[] data = fileDTO.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(data);
    }
}
