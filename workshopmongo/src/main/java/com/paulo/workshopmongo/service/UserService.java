package com.paulo.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paulo.workshopmongo.domain.User;
import com.paulo.workshopmongo.dto.UserDto;
import com.paulo.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();

	}

	public User findById(String id) {
		Optional<User> userOptional = repo.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {

			return null;
		}
	}

	public User insert(User obj) {

		return repo.insert(obj);
	}
	
	public void delete(String id) {
	    repo.deleteById(id);
	
	}

	public User fromDto(UserDto objDto) {

		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	public User update(User obj) {
	    Optional<User> userOptional = repo.findById(obj.getId());
	    
	 
	    if (userOptional.isPresent()) {
	   
	        User existingUser = userOptional.get();
	        
	  
	        updateData(existingUser, obj);
	        
	 
	        return repo.save(existingUser);
	    } else {
	      
	        throw new EmptyResultDataAccessException("Usuário não encontrado com ID: " + obj.getId(), 0);
	    }
	}

	private void updateData(User existingUser, User obj) {
	    
	    existingUser.setName(obj.getName());
	    existingUser.setEmail(obj.getEmail());
	    
	}

}
