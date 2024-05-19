package com.igordalpicolo.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igordalpicolo.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
