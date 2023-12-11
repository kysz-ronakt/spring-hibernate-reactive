package com.reactive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    private Integer id;
    private Integer userId;
    private String content ;
    private  boolean completed;

}
