package io.codefresh.gradleexample.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization_responsible")
public class Organization_responsible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToMany
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
