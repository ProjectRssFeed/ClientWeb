package models;

import java.util.ArrayList;

public class FeedsModel {

	private ArrayList<FeedModel> datas;
	
	public FeedsModel() {
		this.datas = new ArrayList<FeedModel>();
	}

	public ArrayList<FeedModel> getDatas() {
		return datas;
	}

	public void setDatas(FeedModel data) {
		this.datas.add(data);
	}
}
