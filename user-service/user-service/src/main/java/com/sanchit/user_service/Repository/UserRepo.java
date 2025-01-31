package com.sanchit.user_service.Repository;

import com.sanchit.user_service.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);
}
