package pro.ivanov.blog.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import pro.ivanov.blog.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
