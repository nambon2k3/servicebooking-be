package com.fpt.capstone.tourism.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Nếu đã có entity User
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
