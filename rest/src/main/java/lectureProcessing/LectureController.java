package lectureProcessing;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LectureController {

	private final LectureRepository repository;

	LectureController(LectureRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/lectures")
	Resources<Resource<Lecture>> all() {

		List<Resource<Lecture>> lectures = repository.findAll().stream()
			.map(lect -> new Resource<>(lect,
				linkTo(methodOn(LectureController.class).one(lect.getId())).withSelfRel(),
				linkTo(methodOn(LectureController.class).all()).withRel("lectures")))
			.collect(Collectors.toList());
		
		return new Resources<>(lectures,
			linkTo(methodOn(LectureController.class).all()).withSelfRel());
	}

	@PostMapping("/lectures")
	Lecture newLecture(@RequestBody Lecture newLect) {
		return repository.save(newLect);
	}

	@GetMapping("/lectures/{id}")
	Resource<Lecture> one(@PathVariable Long id) {

		Lecture lect = repository.findById(id)
			.orElseThrow(() -> new LectureNotFoundException(id));
		
		return new Resource<>(lect,
			linkTo(methodOn(LectureController.class).one(id)).withSelfRel(),
			linkTo(methodOn(LectureController.class).all()).withRel("lectures"));
	}

	@PutMapping("/lectures/{id}")
	Lecture replaceLecture(@RequestBody Lecture newLect, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(lect -> {
				lect.setLecturerName(newLect.getLecturerName());
				lect.setTheme(newLect.getTheme());
				lect.setAbstractContent(newLect.getAbstractContent());
				lect.setTimeStart(newLect.getTimeStart());
				lect.setIntTimeStart(newLect.getIntTimeStart());
				lect.setTimeEnd(newLect.getTimeEnd());
				lect.setIntTimeEnd(newLect.getIntTimeEnd());
				lect.setAttentedClients(newLect.getAttentedClients());
				return repository.save(lect);
			})
			.orElseGet(() -> {
				newLect.setId(id);
				return repository.save(newLect);
			});
	}

	@DeleteMapping("/lectures/{id}")
	void deleteLecture(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
