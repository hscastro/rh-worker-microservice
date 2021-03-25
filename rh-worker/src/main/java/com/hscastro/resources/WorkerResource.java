package com.hscastro.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hscastro.entities.Worker;
import com.hscastro.repositories.WorkerRepository;


@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = LoggerFactory.getLogger(Worker.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerRepository workerRepository;

	
	public ResponseEntity<Worker> save(@RequestBody Worker worker) {
		Worker w = workerRepository.save(worker);
		return ResponseEntity.ok(w);
	}
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Worker> findById(@PathVariable Long id){
		
//		try {
//			Thread.sleep(3000);
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		logger.info("PORT = "+env.getProperty("local.server.port"));
		
		Worker obj = workerRepository.findById(id).get();
		return ResponseEntity.ok(obj);		
	}
	
	@GetMapping(value = "/configs")	
	public ResponseEntity<Void> getConfigs(){
		//logger.info("CONFIG = "+testConfig);
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping	
	public ResponseEntity<List<Worker>> findAll(){
		List<Worker> lista = workerRepository.findAll();
		return ResponseEntity.ok(lista);		
	}
	
}
