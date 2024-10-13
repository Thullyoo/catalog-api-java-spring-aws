package com.thullyoo.owner_catalog.services.aws;

import com.thullyoo.owner_catalog.exceptions.UploadImageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;

@Slf4j
@Service
public class S3UploadImage {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public String uploadImage(MultipartFile file, String ownerId, Long productId){
        if (!isImage(file)){
            throw new RuntimeException("Tipo de arquivo não permitido");
        }
        String filename = ownerId + "_product_" + productId;

        try {
            PutObjectRequest putObject = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            s3Client.putObject(putObject, RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes())));
            GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.utilities().getUrl(getUrlRequest).toString();

        }catch (Exception e){
            System.out.println("Erro durante o upload do arquivo");
            throw new UploadImageException(e.getMessage());
        }

    }

    public boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") ||
                contentType.equals("image/png") ||
                contentType.equals("image/jpg"));
    }

}
