package com.rithin.springboot.practise.repository;

import com.rithin.springboot.practise.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
