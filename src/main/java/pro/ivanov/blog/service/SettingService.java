package pro.ivanov.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ivanov.blog.entity.Setting;
import pro.ivanov.blog.repository.SettingRepository;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
public class SettingService {
    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return this.settingRepository.count();
    }

    @Transactional(readOnly = true)
    public Setting getSetting(String name) {
        return this.settingRepository.findByName(name).orElseThrow();
    }

    public Setting createSetting(String name, String value) {
        Setting setting = new Setting();

        setting.setName(name);
        setting.setValue(value);

        return this.settingRepository.save(setting);
    }

    public Setting updateSetting(String name, String value) {
        Setting setting = this.getSetting(name);

        setting.setValue(value);

        return this.settingRepository.save(setting);
    }
}
