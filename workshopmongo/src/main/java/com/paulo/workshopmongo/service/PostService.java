package com.paulo.workshopmongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.workshopmongo.domain.Post;
import com.paulo.workshopmongo.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> userOptional = repo.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {

			return null;
		}
	}
}
