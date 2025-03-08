package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.AppConfig;
import com.buildtechknowledge.spring.data.mongodb.repository.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    public List<AppConfig> getAllAppConfigs() {
        return appConfigRepository.findAll();
    }

    public Optional<AppConfig> getAppConfigById(String id) {
        return appConfigRepository.findById(id);
    }

    public AppConfig createAppConfig(AppConfig appConfig) {
        return appConfigRepository.save(appConfig);
    }

    public AppConfig updateAppConfig(String id, AppConfig updatedAppConfig) {
        return appConfigRepository.findById(id)
                .map(appConfig -> {
                    appConfig.setUrl(updatedAppConfig.getUrl());
                    appConfig.setUsername(updatedAppConfig.getUsername());
                    appConfig.setAccessToken(updatedAppConfig.getAccessToken());
                    return appConfigRepository.save(appConfig);
                }).orElseGet(() -> {
                    updatedAppConfig.setId(id);
                    return appConfigRepository.save(updatedAppConfig);
                });
    }

    public void deleteAppConfig(String id) {
        appConfigRepository.deleteById(id);
    }
}
