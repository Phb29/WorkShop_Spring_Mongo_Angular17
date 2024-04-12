package com.paulo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulo.workshopmongo.domain.Post;
import com.paulo.workshopmongo.domain.User;
import com.paulo.workshopmongo.dto.AuthorDto;
import com.paulo.workshopmongo.dto.CommnetDto;
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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GTM"));
		repo.deleteAll();
		postRepo.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		repo.saveAll(Arrays.asList(maria, alex, bob));

		Post post2 = new Post(null, sdf.parse("2018-03-21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDto(maria));

		CommnetDto c1 = new CommnetDto("pedewartiu viagemd", sdf.parse("2018-03-21"), new AuthorDto(alex));
		CommnetDto c2 = new CommnetDto("pedewartiu viagemdsasasas", sdf.parse("2018-08-21"), new AuthorDto(bob));
		CommnetDto c3 = new CommnetDto("pedewartiu viagemdffffff", sdf.parse("2018-05-21"), new AuthorDto(alex));
		post2.getComments().addAll(Arrays.asList(c1, c2));
		
		postRepo.saveAll(Arrays.asList(post2));

		maria.getPost().addAll(Arrays.asList(post2));
		repo.save(maria);
	}

}
