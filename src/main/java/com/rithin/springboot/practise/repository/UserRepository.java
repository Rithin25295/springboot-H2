package com.rithin.springboot.practise.repository;

import com.rithin.springboot.practise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


}
