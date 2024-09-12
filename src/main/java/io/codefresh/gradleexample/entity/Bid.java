package io.codefresh.gradleexample.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bidId")
    private UUID id;

    @Column(name = "bidName")
    private String name;

    @Column(name = "bidDescription")
    private String description;

    @Column(name = "bidStatus")
    private String status;

    @ManyToOne
    @JoinColumn(name = "tenderId")
    private Tender tender;

    @Column(name = "bidAuthorType")
    private String author_type;

    @ManyToOne
    @JoinColumn(name = "bidAuthorId")
    private Employee author;

    @Column(name = "bidVersion")
    private int version;

    @Column(name = "created_at")
    private Timestamp created_at;



}
