package com.reactive.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Tutorials")
public class Tutorial {

    @Id
    private int id;
    private String title;
    private String description;
    private boolean published;

    /*@OneToOne
    private Student student;*/
}
