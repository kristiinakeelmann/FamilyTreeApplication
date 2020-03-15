package com.example.demo;


class PersonNotFoundException extends RuntimeException {

    PersonNotFoundException(Long id) {
        super("Could not find person " + id);
    }
}