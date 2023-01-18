package com.example.snmpManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.snmpManager.entities.Active;

public interface ActiveRepository extends JpaRepository<Active, Long> {

}
