package com.robp.serviceManager.controller;

import com.robp.serviceManager.domain.Response;
import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.enumeration.Status;
import com.robp.serviceManager.service.impl.ApplicationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private ApplicationServiceImpl applicationService;

    @GetMapping("/list")
    public ResponseEntity<Response> getApplications(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("applications", applicationService.listApplication(10)))
                        .message("Applications retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> pingApplication(@RequestBody @Valid ApplicationEntity application){

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("application", applicationService.createApplication(application)))
                        .message("Application created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingApplication(
            @PathVariable("ipAddress") String ipAddress,
            @RequestParam(name = "portNumber", required = true, defaultValue = "80") int portNumber) throws IOException {

        ApplicationEntity applicationEntity = applicationService.ping(ipAddress, portNumber);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("application", applicationEntity))
                        .message(applicationEntity.getStatus() == Status.SERVICE_UP ? "Ping success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
