package com.needle.oneline.src.user.repo;

import com.needle.oneline.src.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findBySocialId(String socialId);
}
