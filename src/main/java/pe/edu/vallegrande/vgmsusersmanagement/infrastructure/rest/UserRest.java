package pe.edu.vallegrande.vgmsusersmanagement.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vgmsusersmanagement.application.service.UserService;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserStatus;
import pe.edu.vallegrande.vgmsusersmanagement.domain.model.User;
import pe.edu.vallegrande.vgmsusersmanagement.infrastructure.dto.ApiResponse;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
        RequestMethod.PATCH })
@Slf4j
public class UserRest {

    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtener todos los usuarios
     */
    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<User>>>> getAllUsers() {
        log.info("Endpoint GET /api/users - Obteniendo todos los usuarios");
        return userService.getAllUsers()
                .collectList()
                .map(users -> {
                    log.info("Se obtuvieron {} usuarios", users.size());
                    ApiResponse<List<User>> response = ApiResponse.success(
                            "Usuarios obtenidos exitosamente",
                            users);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    log.error("Error al obtener usuarios: {}", error.getMessage(), error);
                    ApiResponse<List<User>> response = ApiResponse.error(
                            "Error al obtener usuarios: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

    /**
     * Obtener usuarios por estado (ACTIVE o INACTIVE)
     */
    @GetMapping("/status/{status}")
    public Mono<ResponseEntity<ApiResponse<List<User>>>> getUsersByStatus(@PathVariable String status) {
        log.info("Endpoint GET /api/users/status/{} - Obteniendo usuarios por estado", status);
        try {
            UserStatus userStatus = UserStatus.valueOf(status.toUpperCase());
            return userService.getUsersByStatus(userStatus)
                    .collectList()
                    .map(users -> {
                        log.info("Se obtuvieron {} usuarios con estado {}", users.size(), status);
                        ApiResponse<List<User>> response = ApiResponse.success(
                                "Usuarios con estado " + status + " obtenidos exitosamente",
                                users);
                        return ResponseEntity.ok(response);
                    })
                    .onErrorResume(error -> {
                        log.error("Error al obtener usuarios por estado: {}", error.getMessage(), error);
                        ApiResponse<List<User>> response = ApiResponse.error(
                                "Error al obtener usuarios por estado: " + error.getMessage());
                        return Mono.just(ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(response));
                    });
        } catch (IllegalArgumentException e) {
            log.error("Estado inválido: {}", status);
            ApiResponse<List<User>> response = ApiResponse.error(
                    "Estado inválido. Use ACTIVE o INACTIVE");
            return Mono.just(ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response));
        }
    }

    /**
     * Obtener usuario por ID
     */
    @GetMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<User>>> getUserById(@PathVariable String userId) {
        log.info("Endpoint GET /api/users/{} - Obteniendo usuario por ID", userId);
        return userService.getUserById(userId)
                .map(user -> {
                    log.info("Usuario encontrado: {}", userId);
                    ApiResponse<User> response = ApiResponse.success(
                            "Usuario obtenido exitosamente",
                            user);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Usuario no encontrado: {}", userId);
                    ApiResponse<User> response = ApiResponse.error(
                            "Usuario no encontrado con ID: " + userId);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(response));
                }))
                .onErrorResume(error -> {
                    log.error("Error al obtener usuario: {}", error.getMessage(), error);
                    ApiResponse<User> response = ApiResponse.error(
                            "Error al obtener usuario: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

    /**
     * Crear un nuevo usuario
     */
    @PostMapping
    public Mono<ResponseEntity<ApiResponse<User>>> createUser(@RequestBody User user) {
        log.info("Endpoint POST /api/users - Creando nuevo usuario: {}", user.getUserName());
        return userService.createUser(user)
                .map(createdUser -> {
                    log.info("Usuario creado exitosamente con ID: {}", createdUser.getUserId());
                    ApiResponse<User> response = ApiResponse.success(
                            "Usuario creado exitosamente",
                            createdUser);
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(response);
                })
                .onErrorResume(error -> {
                    log.error("Error al crear usuario: {}", error.getMessage(), error);
                    ApiResponse<User> response = ApiResponse.error(
                            "Error al crear usuario: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

    /**
     * Actualizar usuario existente
     */
    @PutMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<User>>> updateUser(
            @PathVariable String userId,
            @RequestBody User user) {
        log.info("Endpoint PUT /api/users/{} - Actualizando usuario", userId);
        return userService.updateUser(userId, user)
                .map(updatedUser -> {
                    log.info("Usuario actualizado exitosamente: {}", userId);
                    ApiResponse<User> response = ApiResponse.success(
                            "Usuario actualizado exitosamente",
                            updatedUser);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Usuario no encontrado para actualizar: {}", userId);
                    ApiResponse<User> response = ApiResponse.error(
                            "Usuario no encontrado con ID: " + userId);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(response));
                }))
                .onErrorResume(error -> {
                    log.error("Error al actualizar usuario: {}", error.getMessage(), error);
                    ApiResponse<User> response = ApiResponse.error(
                            "Error al actualizar usuario: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

    /**
     * Eliminar usuario lógicamente (cambiar estado a INACTIVE)
     */
    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<ApiResponse<User>>> deleteUser(@PathVariable String userId) {
        log.info("Endpoint DELETE /api/users/{} - Eliminando usuario lógicamente", userId);
        return userService.deleteUser(userId)
                .map(deletedUser -> {
                    log.info("Usuario eliminado lógicamente: {}", userId);
                    ApiResponse<User> response = ApiResponse.success(
                            "Usuario eliminado exitosamente",
                            deletedUser);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Usuario no encontrado para eliminar: {}", userId);
                    ApiResponse<User> response = ApiResponse.error(
                            "Usuario no encontrado con ID: " + userId);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(response));
                }))
                .onErrorResume(error -> {
                    log.error("Error al eliminar usuario: {}", error.getMessage(), error);
                    ApiResponse<User> response = ApiResponse.error(
                            "Error al eliminar usuario: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

    /**
     * Restaurar usuario (cambiar estado a ACTIVE)
     */
    @PatchMapping("/{userId}/restore")
    public Mono<ResponseEntity<ApiResponse<User>>> restoreUser(@PathVariable String userId) {
        log.info("Endpoint PATCH /api/users/{}/restore - Restaurando usuario", userId);
        return userService.restoreUser(userId)
                .map(restoredUser -> {
                    log.info("Usuario restaurado exitosamente: {}", userId);
                    ApiResponse<User> response = ApiResponse.success(
                            "Usuario restaurado exitosamente",
                            restoredUser);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Usuario no encontrado para restaurar: {}", userId);
                    ApiResponse<User> response = ApiResponse.error(
                            "Usuario no encontrado con ID: " + userId);
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(response));
                }))
                .onErrorResume(error -> {
                    log.error("Error al restaurar usuario: {}", error.getMessage(), error);
                    ApiResponse<User> response = ApiResponse.error(
                            "Error al restaurar usuario: " + error.getMessage());
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(response));
                });
    }

}
