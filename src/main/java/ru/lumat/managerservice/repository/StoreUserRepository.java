package ru.lumat.managerservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lumat.managerservice.entity.StoreUser;

import java.util.Optional;

@Repository
public interface StoreUserRepository extends CrudRepository<StoreUser, Integer> {

    Optional<StoreUser> findByUsername(String username);
}
