package com.koreait.store.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Base64;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private Integer no;
    private String originalFilename;
    private LocalDateTime uploadedAt;
    @ToString.Exclude
    private byte[] data;
}







