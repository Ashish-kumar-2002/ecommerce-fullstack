//package com.ecommerce.project.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@Service
//public class FileServiceImpl  implements   FileService{
//
//    @Override
//    public String uploadImage(String path, MultipartFile file) throws IOException {
//        String originalFileName = file.getOriginalFilename();
//
//        String randomId = UUID.randomUUID().toString();
//        //mat.jpg --> 1234--->1234.jpg
//        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
//        String filePath = path + File.separator + fileName;
//
//
//        File folder = new File(path);
//        if (!folder.exists())
//            folder.mkdir();
//
//        Files.copy(file.getInputStream(), Paths.get(filePath));
//
//        return fileName;
//    }
//
//}



package com.ecommerce.project.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "ecommerce/products",
                        "resource_type", "image"
                )
        );

        return uploadResult.get("secure_url").toString();
    }
}
