package pe.edu.vallegrande.vgmsusersmanagement.application.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vgmsusersmanagement.application.service.UserService;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserStatus;
import pe.edu.vallegrande.vgmsusersmanagement.domain.model.User;
import pe.edu.vallegrande.vgmsusersmanagement.infrastructure.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<User> getAllUsers() {
        log.info("Obteniendo todos los usuarios");
        return userRepository.findAll()
                .doOnComplete(() -> log.info("Usuarios obtenidos exitosamente"))
                .doOnError(error -> log.error("Error al obtener usuarios: {}", error.getMessage()));
    }

    @Override
    public Flux<User> getUsersByStatus(UserStatus status) {
        log.info("Obteniendo usuarios por estado: {}", status);
        return userRepository.findByStatus(status)
                .doOnComplete(() -> log.info("Usuarios filtrados por estado {} obtenidos exitosamente", status))
                .doOnError(error -> log.error("Error al obtener usuarios por estado: {}", error.getMessage()));
    }

    @Override
    public Mono<User> getUserById(String userId) {
        log.info("Obteniendo usuario por ID: {}", userId);
        return userRepository.findById(userId)
                .doOnSuccess(user -> {
                    if (user != null) {
                        log.info("Usuario encontrado: {}", userId);
                    } else {
                        log.warn("Usuario no encontrado: {}", userId);
                    }
                })
                .doOnError(error -> log.error("Error al obtener usuario por ID: {}", error.getMessage()));
    }

    @Override
    public Mono<User> createUser(User user) {
        log.info("Creando nuevo usuario: {}", user.getUserName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        if (user.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        }
        return userRepository.save(user)
                .doOnSuccess(savedUser -> log.info("Usuario creado exitosamente con ID: {}", savedUser.getUserId()))
                .doOnError(error -> log.error("Error al crear usuario: {}", error.getMessage()));
    }

    @Override
    public Mono<User> updateUser(String userId, User user) {
        log.info("Actualizando usuario con ID: {}", userId);
        return userRepository.findById(userId)
                .flatMap(existingUser -> {
                    existingUser.setInstitutionId(user.getInstitutionId());
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setDocumentType(user.getDocumentType());
                    existingUser.setDocumentNumber(user.getDocumentNumber());
                    existingUser.setPhone(user.getPhone());
                    existingUser.setAddress(user.getAddress());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setUserName(user.getUserName());
                    // No actualizar el rol - debe permanecer inmutable después de la creación
                    // existingUser.setRole(user.getRole());
                    // Solo actualizar el status si viene en la petición (no es null)
                    if (user.getStatus() != null) {
                        existingUser.setStatus(user.getStatus());
                    }
                    existingUser.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(existingUser);
                })
                .doOnSuccess(updatedUser -> log.info("Usuario actualizado exitosamente: {}", userId))
                .doOnError(error -> log.error("Error al actualizar usuario: {}", error.getMessage()));
    }

    @Override
    public Mono<User> deleteUser(String userId) {
        log.info("Eliminando lógicamente usuario con ID: {}", userId);
        return userRepository.findById(userId)
                .flatMap(user -> {
                    user.setStatus(UserStatus.INACTIVE);
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                })
                .doOnSuccess(deletedUser -> log.info("Usuario eliminado lógicamente: {}", userId))
                .doOnError(error -> log.error("Error al eliminar usuario: {}", error.getMessage()));
    }

    @Override
    public Mono<User> restoreUser(String userId) {
        log.info("Restaurando usuario con ID: {}", userId);
        return userRepository.findById(userId)
                .flatMap(user -> {
                    user.setStatus(UserStatus.ACTIVE);
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                })
                .doOnSuccess(restoredUser -> log.info("Usuario restaurado exitosamente: {}", userId))
                .doOnError(error -> log.error("Error al restaurar usuario: {}", error.getMessage()));
    }
}
