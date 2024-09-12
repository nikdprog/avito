package io.codefresh.gradleexample.entity;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import io.codefresh.gradleexample.service.ServiceAttributeConverter;
import org.hibernate.annotations.GenericGenerator;
import io.codefresh.gradleexample.entity.enums.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Entity
@Table(name = "tender")
public class Tender {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "tenderId")
    private UUID id;

    @Column(name = "tenderName")
    private String name;

    @Column(name = "tenderDescription")
    private String description;

    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "tenderStatus")
    private tender_status status;

    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "tenderServiceType")
    private service_type serviceType;

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public tender_status getStatus() {
        return status;
    }

    public service_type getServiceType() {
        return serviceType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public int getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(tender_status status) {
        this.status = status;
    }

    public void setServiceType(service_type serviceType) {
        this.serviceType = serviceType;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}

