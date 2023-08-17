package com.sefa.Health.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="hospital_id")
    private Long hospitalId;
    @Column(name="hospital_name")
    private String hospitalName;
    private String location;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Doctor> doctorList = new ArrayList<>();

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servicee> servicees;

    private String imagePath;



}
