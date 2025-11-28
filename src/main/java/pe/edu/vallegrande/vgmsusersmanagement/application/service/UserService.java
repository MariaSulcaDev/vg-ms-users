package pe.edu.vallegrande.vgmsusersmanagement.application.service;

import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserStatus;
import pe.edu.vallegrande.vgmsusersmanagement.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserService {

    Flux<User> getAllUsers();

    Flux<User> getUsersByStatus(UserStatus status);

    Mono<User> getUserById(String userId);

    Mono<User> createUser(User user);

    Mono<User> updateUser(String userId, User user);

    Mono<User> deleteUser(String userId);

    Mono<User> restoreUser(String userId);
}
