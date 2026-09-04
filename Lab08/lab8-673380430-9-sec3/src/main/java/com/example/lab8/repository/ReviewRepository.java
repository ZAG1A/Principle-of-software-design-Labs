package com.example.lab8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab8.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {}