package section3;

/*
 * id, title, closed를 가지고 있는 OnlineClass
 */
public class OnlineClass {

	private Integer id;
	private String title;
	private boolean closed;
	
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
