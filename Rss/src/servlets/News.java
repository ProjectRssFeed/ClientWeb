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
import org.json.simple.parser.ParseException;

import models.NewModel;
import models.NewsModel;

@WebServlet("/News")
public class News extends HttpServlet {

	private static final long serialVersionUID = 4202661542092838646L;

	public News() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context =  this.getServletContext();
		String id = request.getParameter("id");
        String title = request.getParameter("title");
        System.out.println("title : "+title+" id : "+id);
			try {
				this.getNews(request, response, id, title);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		context.getRequestDispatcher("/WEB-INF/news.jsp").forward(request, response);
	}

	private void getNews(HttpServletRequest request, HttpServletResponse rep, String id, String title) throws IOException, ParseException {
		String url = "http://127.0.0.1:8443/v0.1/rss/"+id;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

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
		NewsModel news = new NewsModel();
        Iterator<JSONObject> datas = result.iterator();
		while (datas.hasNext()) {
			JSONObject data = datas.next();
			NewModel model = new NewModel();
			model.setDescription(data.get("Description").toString());
			model.setLink(data.get("Link").toString());
			model.setTitle(data.get("Title").toString());
			System.out.println("Data : "+data);
			System.out.println("Description : "+model.getDescription());
			System.out.println("Id : "+model.getLink());
			System.out.println("Title : "+model.getTitle());
			news.setDatas(model);
		}
		news.setParentTitle(title);
		request.getSession().setAttribute("news", news);
		return;
	}

}
