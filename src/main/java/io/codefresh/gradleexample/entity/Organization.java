package io.codefresh.gradleexample.entity;

import javax.persistence.*;
import io.codefresh.gradleexample.entity.enums.organization_type;

import java.sql.Timestamp;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private organization_type type;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

}
