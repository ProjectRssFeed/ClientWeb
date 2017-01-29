<%@ page import="java.util.ArrayList"%>
<%@ page import="models.*"%>
<%@ page import="servlets.News"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>My News</title>
</head>
<body>
	<%@ include file="header.jsp"%>
			<%
		NewsModel news = (NewsModel) session.getAttribute("news");
	%>
	<h2><%=news.getParentTitle()%></h2><br>


	<%
		for (int i = 0; i < news.getDatas().size(); i++) {
	%>
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="heading<%=i%>">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="#collapse<%=i%>" aria-expanded="false"
						aria-controls="collapse<%=i%>"> <%=news.getDatas().get(i).getTitle()%>
					</a>
				</h4>
			</div>
			<div id="collapse<%=i%>" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body">
				<div><%=news.getDatas().get(i).getDescription()%></div>
				<a href="<%=news.getDatas().get(i).getLink()%>"> <%=news.getDatas().get(i).getLink()%></a>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
	<%@ include file="footer.jsp"%>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"
		integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>