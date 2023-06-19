package com.example.conferencesimulation.repositories;

import com.example.conferencesimulation.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByLogin(String login);
}
