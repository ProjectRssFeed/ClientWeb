package models;

public class FeedModel {

	private String id;
	private String description;
	private String title;
	
	public FeedModel() {
		this.setId(null);
		this.setDescription(null);
		this.setTitle(null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
