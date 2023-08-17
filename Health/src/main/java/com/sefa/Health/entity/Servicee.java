package com.sefa.Health.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")

public class Servicee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="service_Id")
    private Long serviceId;

    @Column(name="service_name",nullable = false)
    private String serviceName;


    private String descriptions;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    @JsonBackReference
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name="doctor_id" )
    @JsonIgnore
    private Doctor doctor;







}
