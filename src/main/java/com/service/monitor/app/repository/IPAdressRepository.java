package com.service.monitor.app.repository;

import com.service.monitor.app.domain.IPAdress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPAdressRepository extends CrudRepository<IPAdress, Long> {

    @Query("SELECT ip FROM IPAdress ip WHERE ip.adress =:adress")
    List<IPAdress> findByIpAdress(@Param("adress") String adress);
}
