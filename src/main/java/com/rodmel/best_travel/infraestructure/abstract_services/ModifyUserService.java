package com.rodmel.best_travel.infraestructure.abstract_services;

import com.rodmel.best_travel.domain.repositories.mongo.AppUserRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModifyUserService {


    Map<String, Boolean> enabled(String username);
    Map<String, Set<String>> addRole(String username, String role);
    Map<String, Set<String>> removeRole(String username,String role);

}
