package com.robp.serviceManager.domain.entity;

import com.robp.serviceManager.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "services")
public class ApplicationEntity {
    @Id
    @SequenceGenerator(
            name = "service_id_sequence",
            sequenceName = "service_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "service_id_sequence"
    )
    private Long id;

    @NotEmpty(message = "IP address can't be empty")
    private String ipAddress;
    @NotEmpty(message = "Port number can't be empty")
    @Column(unique = true)
    private String portNumber;
    private String name;
    private String description;
    private String imageUrl;
    private Status status;

}
