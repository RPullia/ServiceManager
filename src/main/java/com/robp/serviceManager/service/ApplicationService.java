package com.robp.serviceManager.service;

import com.robp.serviceManager.domain.entity.ApplicationEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface ApplicationService {

    ApplicationEntity createApplication(ApplicationEntity applicationEntity);
    Collection<ApplicationEntity> listApplication(int limit);
    Optional<ApplicationEntity> getApplication(Long id);
    ApplicationEntity updateApplication(ApplicationEntity applicationEntity);
    void deleteApplication(Long id);
    ApplicationEntity ping(String ipAddress, int port) throws IOException;

}
