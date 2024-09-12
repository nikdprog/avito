package io.codefresh.gradleexample.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tender")
public class Tender {
    @Id
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tenderId")
    private UUID id;

    @Column(name = "tenderName")
    private String name;

    @Column(name = "tenderDescription")
    private String description;

    @Column(name = "tenderStatus")
    private String status;

    @Column(name = "tenderServiceType")
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;

    @Column(name = "tenderVersion")
    private int version;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }







    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }






    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Organization getOrganization() {
        return organization;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }





    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }



    public String getName() {
        return name;
    }


}

