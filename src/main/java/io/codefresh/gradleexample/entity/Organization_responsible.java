package io.codefresh.gradleexample.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization_responsible")
public class Organization_responsible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;

}
