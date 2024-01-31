package com.robp.serviceManager.controller;

import com.robp.serviceManager.domain.Response;
import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.enumeration.Status;
import com.robp.serviceManager.service.impl.ApplicationServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private ApplicationServiceImpl applicationService;


    @GetMapping("/list")
    public ResponseEntity<Response> listApplications(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("applications", applicationService.listApplications(10)))
                        .message("Applications retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/registrate")
    public ResponseEntity<Response> registrateApplication(@RequestBody @Valid ApplicationEntity application){

        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(now())
                        .data(of("application", applicationService.registrateApplication(application)))
                        .message("Application created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build(), CREATED
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getApplication(@PathVariable("id") Long id)  {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("application", applicationService.getApplication(id)))
                        .message("Application retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteApplication(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted", applicationService.deleteApplication(id)))
                        .message("Application deleted")
                        .status(OK)
                        .statusCode(OK.value())
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

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("fileName") String fileName) throws IOException {
        // Load the image resource from the classpath
        Resource resource = new ClassPathResource("images/" + fileName);
        return Files.readAllBytes(resource.getFile().toPath());
    }


}
