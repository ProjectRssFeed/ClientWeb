package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import models.FeedModel;
import models.FeedsModel;

@WebServlet("/Feeds")
public class Feeds extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeedsModel feedsModel;
	
	public Feeds() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context =  this.getServletContext();
			try {
				this.getFeeds(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		context.getRequestDispatcher("/WEB-INF/feeds.jsp").forward(request, response);
	}

	protected void getFeeds(HttpServletRequest request, HttpServletResponse rep) throws Exception {
		String url = "http://163.5.84.111:8443/v0.1/rss/";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		Object jsonObj = new Object();
		JSONParser parser = new JSONParser();
		jsonObj = parser.parse(response.toString());
        JSONArray result = (JSONArray) jsonObj;
		in.close();
		feedsModel = new FeedsModel();
        Iterator<JSONObject> datas = result.iterator();
		while (datas.hasNext()) {
			JSONObject data = datas.next();
			FeedModel model = new FeedModel();
			model.setDescription(data.get("Description").toString());
			model.setId(data.get("Id").toString());
			model.setTitle(data.get("Title").toString());
			System.out.println("Data : "+data);
			System.out.println("Description : "+model.getDescription());
			System.out.println("Id : "+model.getId());
			System.out.println("Title : "+model.getTitle());
			feedsModel.setDatas(model);
		}
		System.out.println(feedsModel.getDatas().size());
		request.getSession().setAttribute("feeds", feedsModel);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
