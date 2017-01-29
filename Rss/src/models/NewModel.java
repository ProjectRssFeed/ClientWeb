package models;

public class NewModel {
	
	private String link;
	private String description;
	private String title;
	
	public NewModel() {
		this.setLink(null);
		this.setDescription(null);
		this.setTitle(null);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
