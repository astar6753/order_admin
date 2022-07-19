package com.astar.order_admin.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileAPIController {
    //application.properties spring.servlet.multipart.location지정값을 path에 저장
    @Value("${spring.servlet.multipart.location}") String path;
    
    //uri// order/{member/restaurant/dish}/{filename}
    @GetMapping("/img/{type}/{filename}")
    public ResponseEntity<Resource> getImage(
        @PathVariable String type, @PathVariable @Nullable String filename, HttpServletRequest request) {

        Path folderLocation = Paths.get(path+"/"+type);     // folderLocation = /path/{type}
        Path filePath = folderLocation.resolve(filename);   // filePath = path/type/filename = d:/order/member/default.jpg
        Resource r = null;
        try {
            r = new UrlResource(filePath.toUri());
        } catch (Exception e) {
            System.out.println("파일을 찾을 수 없거나 잘못된 파일 경로 입니다.");
        }
        //파일의 실제 경로에 찾아가서 파일의 유형을 가져온다.
        String contentType = null;
        try {
            request.getServletContext().getMimeType(r.getFile().getAbsolutePath());            
            if(contentType == null)
            contentType = "application/octet-stream";
        } catch (Exception e) {
            System.out.println("파일을 찾을 수 없거나 잘못된 파일 경로 입니다.");
        }
        

        return
            ResponseEntity.ok() //결과로 200ok를 설정
            //파일의 타입을 Spring프레임 워크를 통해 파일 유형을 결정
            .contentType(MediaType.parseMediaType(contentType))
            //파일 이름의 표시방법을 설정
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+r.getFilename()+"\"")
            //실제 리소스uri를 body에 포함
            .body(r);
    }
    
    @PutMapping("/img/{type}/upload")
    public Map<String,Object> putImageUpload(
        @PathVariable String type, @RequestPart MultipartFile file) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        //업로드 할 파일 경로 생성 /order/{type}
        Path folderLocation = Paths.get(path+"/"+type);

        String fileName = file.getOriginalFilename();
        String[] fileNameSplit = fileName.split("\\.");
        String ext = fileNameSplit[fileNameSplit.length-1];

        
        if(!ext.equalsIgnoreCase("gif") && !ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("png")){
            resultMap.put("status", false);
            resultMap.put("message","이미지 파일 확장자는 jpg, png, gif만 허용합니다.");
            return resultMap;
        }
        
        Calendar c = Calendar.getInstance();
        //파일 저장시 "타입_타입스탬프.ext"형식으로 저장
        String saveFileName = StringUtils.cleanPath(type+"_"+c.getTimeInMillis()+"."+ext);
        Path target = folderLocation.resolve(saveFileName);
        
        try {
            //파일업로드    //option 이미 존재하면 덮어쓰기
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            //파일 업로드 실패
            resultMap.put("status",false);
            resultMap.put("message",e.getMessage());
            return resultMap;
        }
        
        long fileSize = file.getSize();
        resultMap.put("status",true);
        resultMap.put("message","파일 업로드 완료");
        resultMap.put("saveFileName", saveFileName);
        resultMap.put("originName", fileNameSplit[0]);
        resultMap.put("ext", ext);
        resultMap.put("fileSize", fileSize);
        return resultMap;
    }
    
    @DeleteMapping("/img/{type}/{filename}")
    public Map<String,Object> deleteImageFile(
        @PathVariable String type, @PathVariable String filename) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        String filepath = path+"/"+type+"/"+filename;
        File deleteFile = new File(filepath);
        
        if(deleteFile.exists()) {
            deleteFile.delete();
        }
        else {
            resultMap.put("status",false);
            resultMap.put("message","파일이 존재하지 않습니다.");
            resultMap.put("path",filepath);
            return resultMap;
        }
        resultMap.put("status",true);
        resultMap.put("message","파일을 삭제했습니다.");
        resultMap.put("path",filepath);
        return resultMap;
    }

}
