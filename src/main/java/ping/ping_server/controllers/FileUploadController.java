package ping.ping_server.controllers;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    private static final String BUCKET_NAME = "ping-5ccd1.appspot.com";

    @Autowired
    private StorageClient storageClient;;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Lấy tên file gốc
            String originalFileName = file.getOriginalFilename();

            // Upload file lên Firebase Storage
            String imageUrl = storageClient.bucket(BUCKET_NAME)
                    .create(originalFileName, file.getBytes())
                    .getMediaLink();

            System.out.println(imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
