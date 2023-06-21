package org.ivanovx.bloggable.repository;

import org.ivanovx.bloggable.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.ivanovx.bloggable.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);
}
