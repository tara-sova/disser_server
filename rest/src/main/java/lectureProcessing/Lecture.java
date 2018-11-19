package lectureProcessing;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Lecture {

	private @Id @GeneratedValue Long id;
	private String lecturerName;
	private String theme;
	public String abstractContent;

	public String timeStart;
	public int intTimeStart;
	public String timeEnd;
	public int intTimeEnd;

	public Lecture(String lecturerName, String theme, String abstractContent
			, String timeStart, int intTimeStart, String timeEnd, int intTimeEnd) {
		this.lecturerName = lecturerName;
		this.theme = theme;
		this.abstractContent = abstractContent;

		this.timeStart = timeStart;
		this.intTimeStart = intTimeStart;
		this.timeEnd = timeEnd;
		this.intTimeEnd = intTimeEnd;
	}
}
