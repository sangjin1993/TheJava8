package section4;

import java.time.Duration;

/*
 * 학습에 수료나 얼마나 학습했는지 확인하는 도매인
 */
public class Progress {

	private Duration studyDuration;
	
	private boolean finished;

	public Duration getStudyDuration() {
		return studyDuration;
	}

	public void setStudyDuration(Duration studyDuration) {
		this.studyDuration = studyDuration;
	}

	
}
