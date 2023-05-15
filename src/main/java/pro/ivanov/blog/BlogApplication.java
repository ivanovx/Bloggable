package pro.ivanov.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.ivanov.blog.entity.Role;
import pro.ivanov.blog.entity.User;
import pro.ivanov.blog.repository.UserRepository;

@SpringBootApplication
public class BlogApplication implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args)  {
		if (this.userRepository.count() == 0) {
			User user = new User();

			user.setEmail("csyntax@outlook.com");
			user.setName("Ivan Ivanov");
			user.setRole(Role.ADMIN);
			user.setPassword(this.passwordEncoder.encode("csyntax"));
			user.setUsername("csyntax");

			this.userRepository.save(user);
		}
	}
}
