package com.sefa.Health.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="namee",nullable = false)
    private String name;
    private String surname;

    @Column(name="user_name",nullable = false,unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="country")
    private String country;

    @Column(name="phone_number",nullable = false)
    private String phoneNumber;
    @Column(name="citizenship_number")
    private String citizenshipNumber;

    @OneToMany
    private List<Appointment> appointments;


    private int age;








}
