package com.robp.serviceManager.service.impl;

import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.repository.ApplicationRepository;
import com.robp.serviceManager.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationEntity createApplication(ApplicationEntity applicationEntity) {
        // todo: validation
        log.info("Creating a new application");
        applicationEntity.setImageUrl(setApplicationImageUrl());
        return applicationRepository.save(applicationEntity);
    }

    @Override
    public Collection<ApplicationEntity> list(int limit) {
        return null;
    }

    @Override
    public ApplicationEntity get(Long id) {
        return null;
    }

    @Override
    public ApplicationEntity update(ApplicationEntity applicationEntity) {
        return null;
    }

    @Override
    public ApplicationEntity delete(Long id) {
        return null;
    }

    @Override
    public ApplicationEntity ping(String ipAddress, String port) {
        log.info("Pinging application at: "+ipAddress+":"+port);
        ApplicationEntity applicationEntity = applicationRepository.findByIpAddressAndPortNumber(ipAddress, port);
        return null;
    }

    private String setApplicationImageUrl() {
        // todo
        return null;
    }
}
