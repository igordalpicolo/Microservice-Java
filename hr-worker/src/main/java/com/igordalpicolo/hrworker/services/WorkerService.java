package com.igordalpicolo.hrworker.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.igordalpicolo.hrworker.entities.Worker;
import com.igordalpicolo.hrworker.repositories.WorkerRepository;
import com.igordalpicolo.hrworker.services.exceptions.DatabaseException;
import com.igordalpicolo.hrworker.services.exceptions.ResourceNotFoundException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;

	public List<Worker> findAll() {
		return repository.findAll();
	}

	public Worker findById(Long id) {
		Optional<Worker> obj = repository.findById(id);
		return obj.get();
	}

	public Worker insert(Worker obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			if (!repository.existsById(id))
				throw new ResourceNotFoundException(id);
			repository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Worker update(Long id, Worker obj) {
		try {
			Worker entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Worker entity, Worker obj) {
		entity.setName(obj.getName());
		entity.setDailyIncome(obj.getDailyIncome());

	}
}
