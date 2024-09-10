package io.codefresh.gradleexample.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public String getUsername() {return username;}
    public Long getId() {
        return id;
    }
}
