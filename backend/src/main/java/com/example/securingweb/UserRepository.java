package com.example.securingweb;

import com.example.securingweb.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByUsername(String username);

}
