package com.kkngai.blogjava.respository;

import com.kkngai.blogjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : kkngai
 * @created : 10/1/2021, 星期日
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user u WHERE u.username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);
}
