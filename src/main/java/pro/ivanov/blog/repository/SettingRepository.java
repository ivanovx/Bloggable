package pro.ivanov.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ivanov.blog.entity.Setting;

import java.util.Optional;

@Repository
public interface SettingRepository extends CrudRepository<Setting, Long> {
    Optional<Setting> findByName(String name);
}
