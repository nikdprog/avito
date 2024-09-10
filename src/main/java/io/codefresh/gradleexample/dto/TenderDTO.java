package io.codefresh.gradleexample.dto;


import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TenderDTO {
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    private String name;
    private String description;
    private String status;
    private String serviceType;

    private Long organizationId;
    private String creatorUsername;
    /*
    public TenderDTO(Long id, String name, String description, String status, String serviceType, Organization organization, Employee employee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.serviceType = serviceType;
        this.organization = organization;
        this.creator = employee;

    }\

     */

}
