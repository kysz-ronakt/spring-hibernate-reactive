package com.reactive.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Student {

    @Id
    private Long id;
    private String title;
    private Long tutorialId; // Foreign key referencing Author

}
