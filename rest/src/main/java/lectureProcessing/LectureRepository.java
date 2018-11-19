package lectureProcessing;

import org.springframework.data.jpa.repository.JpaRepository;

interface LectureRepository extends JpaRepository<Lecture, Long> {

}
