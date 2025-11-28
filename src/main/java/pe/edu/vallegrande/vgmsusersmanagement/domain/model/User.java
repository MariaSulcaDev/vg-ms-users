package pe.edu.vallegrande.vgmsusersmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserRole;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String userId;

    private String institutionId;

    private String firstName;

    private String lastName;

    private String documentType;

    private String documentNumber;

    private String phone;

    private String address;

    private  String email;

    private String userName;

    private UserRole role;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
