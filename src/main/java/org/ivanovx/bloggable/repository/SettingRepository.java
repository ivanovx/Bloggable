package org.ivanovx.bloggable.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ivanovx.bloggable.entity.Setting;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingRepository extends CrudRepository<Setting, Long> {
    List<Setting> findAll();

    Optional<Setting> findByName(String name);
}
