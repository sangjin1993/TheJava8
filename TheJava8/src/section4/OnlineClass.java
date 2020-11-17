package section4;

import java.util.Optional;

/*
 * id, title, closed를 가지고 있는 OnlineClass
 */
public class OnlineClass {

	private Integer id;
	private String title;
	private boolean closed;
	// 얼마나 오랫동안 학습했는지
	public Progress progress;
	
	public OnlineClass(Integer id, String title, boolean closed) {
		this.id = id;
		this.title = title;
		this.closed = closed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public Optional<Progress> getProgress() {
		// ofNullable은 null을 넣으면 Optional에 비어있는거와 같은 처리를 해준다.
		return Optional.ofNullable(progress);
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	
}
