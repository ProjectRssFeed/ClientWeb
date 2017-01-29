package models;

import java.util.ArrayList;

public class NewsModel {
private ArrayList<NewModel> datas;
private String parentTitle;
	
	public NewsModel() {
		this.datas = new ArrayList<NewModel>();
		this.parentTitle = "";
	}

	public ArrayList<NewModel> getDatas() {
		return datas;
	}

	public void setDatas(NewModel data) {
		this.datas.add(data);
	}

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
}
