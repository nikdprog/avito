package io.codefresh.gradleexample.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tender")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status; // CREATED, PUBLISHED, CLOSED

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

