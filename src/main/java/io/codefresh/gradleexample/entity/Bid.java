package io.codefresh.gradleexample.entity;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import io.codefresh.gradleexample.entity.enums.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
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

    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "bidStatus")
    private bid_status status;

    @ManyToOne
    @JoinColumn(name = "tenderId")
    private Tender tender;

    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "bidAuthorType")
    private author_type author_type;

    @ManyToOne
    @JoinColumn(name = "bidAuthorId")
    private Employee author;

    @Column(name = "bidVersion")
    private int version;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public bid_status getStatus() {
        return status;
    }

    public void setStatus(bid_status status) {
        this.status = status;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public io.codefresh.gradleexample.entity.enums.author_type getAuthor_type() {
        return author_type;
    }

    public void setAuthor_type(io.codefresh.gradleexample.entity.enums.author_type author_type) {
        this.author_type = author_type;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
