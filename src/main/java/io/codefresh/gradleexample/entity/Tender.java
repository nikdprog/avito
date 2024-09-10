package io.codefresh.gradleexample.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tender")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    @Column(name = "name")
    private String name;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "description")
    private String description;


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

    public Employee getCreator() {
        return creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Column(name = "status")
    private String status; // CREATED, PUBLISHED, CLOSED

    @Column(name = "service_type")
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "version")
    private int version;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    /*
    @Override
    public String toString() {
        return "Tender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", organization=" + organization.getName() +
                ", creator=" + creator.getUsername() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}

