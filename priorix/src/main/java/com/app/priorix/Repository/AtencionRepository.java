package com.app.priorix.Repository;


import com.app.priorix.model.entity.Atencion;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionRepository extends MongoRepository<Atencion, String> {
    

}
