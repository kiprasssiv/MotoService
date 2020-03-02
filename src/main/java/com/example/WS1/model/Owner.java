package com.example.WS1.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "motorcycle_id")
    private UUID motorcycle_id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "telephoneNumber")
    private String telephoneNumber;
    @Column(name = "address")
    private String address;
}