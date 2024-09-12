package io.codefresh.gradleexample.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "organization_responsible")
public class Organization_responsible {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;

}
