package com.koreait.store.controller;

import com.koreait.store.dto.FileDTO;
import com.koreait.store.dto.ProductDTO;
import com.koreait.store.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/upload")
    public void get_upload(){}

    @PostMapping("/upload")
    public String post_upload(ProductDTO productDTO){
        adminService.add_product(productDTO);
        return "redirect:/admin/upload";
    }
    
    
    
    
    /********************************************************************************/
    @GetMapping("/fileupload")
    public void get_fileUpload(){}
    
    @PostMapping("/fileconvert")
    public void post_file_convert(byte[] abc){
        
    }

    @PostMapping("/fileupload")
    public void post_fileUpload(List<MultipartFile> my_files) throws Exception {
        for(MultipartFile my_file : my_files){
            log.error("전송된 파일명:" + my_file.getOriginalFilename());
            log.error("전송된 파일의 크기: "+ my_file.getSize());
            log.error("전송된 파일의 형태: "+ my_file.getContentType());
            adminService.add_file(my_file.getBytes());
        }
    }

    @GetMapping("/filedownload")
    public void get_fileDownload(){}
    
    @ResponseBody
    @GetMapping("/db/{no}")
    public ResponseEntity<byte[]> get_db_file_download(
            @PathVariable("no") Integer fileNo
    ){
        FileDTO fileDTO = adminService.get_file(fileNo);
        byte[] fileData = fileDTO.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(fileData.length);
        headers.setContentDisposition(
                ContentDisposition.attachment().filename("1.jpg").build()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(fileData);
    }

    @GetMapping("/image/{filename}")
    @ResponseBody
    public byte[] get_image(
            @PathVariable("filename") String filename,
            HttpServletResponse response
    ){
        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\KSW_WEB\\Cookie Run Store\\src\\main\\resources\\files\\" + filename+ ".jpg");
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = in.readAllBytes();
            response.setContentType("image/jpeg");
            response.setContentLength(bytes.length);
            response.setHeader("Content-Disposition", "attachment;filename=1.jpg");
            in.close();

            return bytes;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }




}
