package lectureProcessing;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LectureRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Lecture("Bilbo Baggins", "burglar", "about ring", "22:30", 2230, "23:30", 2330)));
			log.info("Preloading " + repository.save(new Lecture("Frodo Baggins", "thief", "about kidness", "23:30", 2330, "24:00", 2400)));
		};
	}
}
