package com.sajandeep.crudReactSpring.repository;

import com.sajandeep.crudReactSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
