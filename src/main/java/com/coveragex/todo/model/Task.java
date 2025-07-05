package com.coveragex.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id_task")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
}
