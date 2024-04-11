package com.paulo.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.paulo.workshopmongo.domain.User;
import com.paulo.workshopmongo.dto.UserDto;
import com.paulo.workshopmongo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {

		List<User> list = service.findAll();
		List<UserDto> userlist = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(userlist);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		User obj = service.findById(id);

		return ResponseEntity.ok().body(new UserDto(obj));

	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto) {

		User user = service.fromDto(objDto);

		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		 service.delete(id);

		return ResponseEntity.noContent().build();

	}
}
