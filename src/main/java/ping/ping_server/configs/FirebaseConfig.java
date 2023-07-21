package ping.ping_server.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public FirebaseApp firebaseApp() throws IOException {
        ClassPathResource serviceAccountResource = new ClassPathResource("serviceAccountKey.json");
        InputStream serviceAccountStream = serviceAccountResource.getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public StorageClient storageClient() {
        return StorageClient.getInstance();
    }
}
