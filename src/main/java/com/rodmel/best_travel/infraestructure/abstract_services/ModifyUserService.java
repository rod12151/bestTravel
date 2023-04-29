package com.rodmel.best_travel.infraestructure.abstract_services;

import com.rodmel.best_travel.domain.repositories.mongo.AppUserRepository;

import java.util.List;
import java.util.Map;

public interface ModifyUserService {


    Map<String, Boolean> enabled(String username);
    Map<String, List<String>> addRole(String username,String role);
    Map<String, List<String>> removeRole(String username,String role);

}
