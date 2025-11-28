package pe.edu.vallegrande.vgmsusersmanagement.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.vgmsusersmanagement.domain.enums.UserStatus;
import pe.edu.vallegrande.vgmsusersmanagement.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByDocumentNumber(String documentNumber);

    Mono<User> findByUserName(String userName);

    Flux<User> findByInstitutionId(String institutionId);

    Flux<User> findByRole(String role);

    Flux<User> findByStatus(UserStatus status);
}
