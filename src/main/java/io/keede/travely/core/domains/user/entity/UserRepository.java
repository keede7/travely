package io.keede.travely.core.domains.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user " +
            "from User user " +
            "where user.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

}
