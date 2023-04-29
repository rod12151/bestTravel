package com.rodmel.best_travel.domain.repositories.mongo;

import com.rodmel.best_travel.domain.entities.documents.AppUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AppUserRepository extends MongoRepository<AppUserDocument, String> {

    Optional<AppUserDocument> findByUsername(String username);
}
