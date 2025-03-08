package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.model.AppConfig;
import com.buildtechknowledge.spring.data.mongodb.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appconfigs")
public class AppConfigController {

    @Autowired
    private AppConfigService appConfigService;

    @GetMapping
    public List<AppConfig> getAllAppConfigs() {
        return appConfigService.getAllAppConfigs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppConfig> getAppConfigById(@PathVariable String id) {
        return appConfigService.getAppConfigById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AppConfig createAppConfig(@RequestBody AppConfig appConfig) {
        return appConfigService.createAppConfig(appConfig);
    }

    @PutMapping("/{id}")
    public AppConfig updateAppConfig(@PathVariable String id, @RequestBody AppConfig appConfig) {
        return appConfigService.updateAppConfig(id, appConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppConfig(@PathVariable String id) {
        appConfigService.deleteAppConfig(id);
        return ResponseEntity.noContent().build();
    }
}
