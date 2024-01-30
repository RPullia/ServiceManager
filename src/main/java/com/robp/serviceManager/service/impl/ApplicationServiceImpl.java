package com.robp.serviceManager.service.impl;

import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.enumeration.Image;
import com.robp.serviceManager.enumeration.Status;
import com.robp.serviceManager.repository.ApplicationRepository;
import com.robp.serviceManager.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationEntity createApplication(ApplicationEntity applicationEntity) {
        // todo: validation
        log.info("Creating a new application {}", applicationEntity.getName());
        applicationEntity.setImageUrl(setApplicationImageUrl());
        return applicationRepository.save(applicationEntity);
    }

    @Override
    public Collection<ApplicationEntity> listApplications(int limit) {
        log.info("Fetching all applications");
        return applicationRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Optional<ApplicationEntity> getApplication(Long id) {
        log.info("Fetching application {}", id);
        return applicationRepository.findById(id);
    }

    @Override
    public ApplicationEntity updateApplication(ApplicationEntity applicationEntity) {
        log.info("Updating a new application {}", applicationEntity.getName());
        return applicationRepository.save(applicationEntity);
    }

    @Override
    public Boolean deleteApplication(Long id) {
        log.info("Deleting application {}", id);
        applicationRepository.deleteById(id);
        return true;
    }

    @Override
    public ApplicationEntity ping(String ipAddress, int port) throws IOException {
        log.info("Pinging application at {}:{}", ipAddress, port);
        ApplicationEntity applicationEntity = applicationRepository.findByIpAddressAndPortNumber(ipAddress, port);

        if (applicationEntity == null) {
            // Handle the case when the applicationEntity is not found
            log.warn("Application not found for {}:{}", ipAddress, port);
            return null;
        }

        InetSocketAddress socketAddress = new InetSocketAddress(ipAddress, port);

        try (Socket socket = new Socket()) {
            // Connect to the socket address with a specified timeout
            socket.connect(socketAddress, 10000);

            // If the connection is successful, the host is reachable
            log.info("Ping successful to {}:{}", ipAddress, port);

            applicationEntity.setStatus(Status.SERVICE_UP);
            applicationRepository.save(applicationEntity);
        } catch (IOException e) {
            // If an IOException occurs, the host is not reachable
            log.info("Ping failed to {}:{} ", ipAddress, port);
            applicationEntity.setStatus(Status.SERVICE_DOWN);
            applicationRepository.save(applicationEntity);
            log.error("Ping failed to {}:{}", ipAddress, port, e);
        }

        return applicationEntity;
    }

    private String setApplicationImageUrl() {
        Image image = Image.IMAGE_LEAF;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/application/images" + image).toUriString();
    }
}
