package com.rodmel.best_travel.infraestructure.services;

import com.rodmel.best_travel.domain.repositories.mongo.AppUserRepository;
import com.rodmel.best_travel.infraestructure.abstract_services.ModifyUserService;
import com.rodmel.best_travel.util.enums.Tables;
import com.rodmel.best_travel.util.exeptions.UserNameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor

public class AppUserService implements ModifyUserService{

    private final AppUserRepository appUserRepository;
    @Override
    public Map<String, Boolean> enabled(String username) {
        var user = this.appUserRepository.findByUsername(username).
                orElseThrow(()-> new UserNameNotFoundException(COLLECTION_NAME));
        user.setEnabled(!user.isEnabled());
        var userSaved = this.appUserRepository.save(user);

        return Collections.singletonMap(userSaved.getUsername(),userSaved.isEnabled());
    }

    @Override
    public Map<String, Set<String>> addRole(String username, String role) {
        var user = this.appUserRepository.findByUsername(username).
                orElseThrow(()-> new UserNameNotFoundException(COLLECTION_NAME));
        user.getRole().getGrantedAuthorities().add(role);
        var userSaved = this.appUserRepository.save(user);
        var authorities = userSaved.getRole().getGrantedAuthorities();
        log.info("User {} add role {}",userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());
        return Collections.singletonMap(userSaved.getUsername(),authorities);

    }

    @Override
    public Map<String, Set<String>> removeRole(String username, String role) {
        var user = this.appUserRepository.findByUsername(username).
                orElseThrow(()-> new UserNameNotFoundException(COLLECTION_NAME));
        user.getRole().getGrantedAuthorities().remove(role);
        var userSaved = this.appUserRepository.save(user);
        var authorities = userSaved.getRole().getGrantedAuthorities();
        log.info("User {} remove role {}",userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());
        return Collections.singletonMap(userSaved.getUsername(),authorities);
    }

    private static final String COLLECTION_NAME ="app_user";
}
