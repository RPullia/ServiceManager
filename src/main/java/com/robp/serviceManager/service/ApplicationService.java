package com.robp.serviceManager.service;

import com.robp.serviceManager.domain.entity.ApplicationEntity;

import java.util.Collection;

public interface ApplicationService {

    ApplicationEntity createApplication(ApplicationEntity applicationEntity);
    Collection<ApplicationEntity> list(int limit);
    ApplicationEntity get(Long id);
    ApplicationEntity update(ApplicationEntity applicationEntity);
    ApplicationEntity delete(Long id);
    ApplicationEntity ping(String ipAddress, String port);

}
