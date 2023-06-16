package com.example.conferencesimulation.repositories;

import com.example.conferencesimulation.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
}
