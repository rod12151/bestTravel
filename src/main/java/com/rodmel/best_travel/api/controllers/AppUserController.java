package com.rodmel.best_travel.api.controllers;

import com.rodmel.best_travel.infraestructure.abstract_services.ModifyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name = "User")
public class AppUserController {
    private final ModifyUserService modifyUserService;
    @Operation(summary = "Enabled or disabled user")
    @PatchMapping("enabled-or-disabled")
    public ResponseEntity<Map<String,Boolean>> enableOrDisabled(@RequestParam String username){
        return ResponseEntity.ok(this.modifyUserService.enabled(username));
    }
    @Operation(summary = "add role user")
    @PatchMapping("add-role")
    public ResponseEntity<Map<String, List<String>>> addRole(@RequestParam String username, @RequestParam String role){
        return ResponseEntity.ok(this.modifyUserService.addRole(username,role));
    }
    @Operation(summary = "remove role user")
    @PatchMapping("remove-role")
    public ResponseEntity<Map<String, List<String>>> removeRoe(@RequestParam String username, @RequestParam String role){
        return ResponseEntity.ok(this.modifyUserService.removeRole(username,role));
    }


}
