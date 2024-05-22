package com.igordalpicolo.hrworker.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.igordalpicolo.hrworker.entities.Worker;
import com.igordalpicolo.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

	@Autowired
	private WorkerService service;
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id){
		Worker obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Worker> insert(@RequestBody Worker obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Worker> update(@PathVariable Long id, @RequestBody Worker obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
