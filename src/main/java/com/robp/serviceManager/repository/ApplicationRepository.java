package com.robp.serviceManager.repository;

import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    Iterable<ApplicationEntity> findByIpAddress(String ipAddress);
    ApplicationEntity findByIpAddressAndPortNumber(String ipAddress, int portNumber);
    Iterable<ApplicationEntity> findByName(String name);
    Iterable<ApplicationEntity> findByStatus(Status status);
}
