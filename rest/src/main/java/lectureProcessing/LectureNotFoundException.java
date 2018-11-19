package lectureProcessing;

class LectureNotFoundException extends RuntimeException {

	LectureNotFoundException(Long id) {
		super("Could not find lecture " + id);
	}
}
