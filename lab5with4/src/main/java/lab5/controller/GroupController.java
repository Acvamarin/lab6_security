package lab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab5.entity.Group;
import lab5.service.GroupService;

@RestController
@RequestMapping(value = "api/group", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

	@Autowired
	private GroupService service;

	@GetMapping
	public ResponseEntity<List<Group>> get() {
		List<Group> entities = service.read();
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Group> getById(@PathVariable long id) {
		Group entity = service.read(id);
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Group> getStudentsBySurname(@PathVariable String name) {
		Group group = service.readByName(name);
		if (group == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(group, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> put(@RequestBody Group entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> post(@RequestBody Group entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
