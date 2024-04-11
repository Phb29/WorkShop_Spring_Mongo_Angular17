package com.paulo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulo.workshopmongo.domain.Post;
import com.paulo.workshopmongo.domain.User;
import com.paulo.workshopmongo.repository.PostRepository;
import com.paulo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository repo;
	@Autowired
	private PostRepository postRepo;


	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat  sdf= new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GTM"));
		repo.deleteAll();
		postRepo.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		Post post2 = new Post(null, sdf.parse("2018-03-21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",maria);


		
		repo.saveAll(Arrays.asList(maria, alex, bob));
		postRepo.saveAll(Arrays.asList(post2));
	}

}
