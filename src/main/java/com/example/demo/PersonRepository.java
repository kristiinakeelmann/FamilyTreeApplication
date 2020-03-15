package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersonRepository extends JpaRepository<Person, Long> {

}