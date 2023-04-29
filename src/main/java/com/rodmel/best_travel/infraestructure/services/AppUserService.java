package com.rodmel.best_travel.infraestructure.services;

import com.rodmel.best_travel.domain.repositories.mongo.AppUserRepository;
import com.rodmel.best_travel.infraestructure.abstract_services.ModifyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@Slf4j
@AllArgsConstructor

public class AppUserService implements ModifyUserService{

    private final AppUserRepository appUserRepository;
    @Override
    public Map<String, Boolean> enabled(String username) {
        return null;
    }

    @Override
    public Map<String, List<String>> addRole(String username, String role) {
        return null;
    }

    @Override
    public Map<String, List<String>> removeRole(String username, String role) {
        return null;
    }
}
