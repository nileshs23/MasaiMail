package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Emails;

@Repository
public interface EmailsRepository extends JpaRepository<Emails, Integer>{

}
