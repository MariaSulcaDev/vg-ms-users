package pe.edu.vallegrande.vgmsusersmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class VgMsUsersManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(VgMsUsersManagementApplication.class, args);
    }

}
