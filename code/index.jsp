<%@page import="at.klu.Book"%>
<%@page import="at.klu.Category"%>
<%@page import="java.util.List"%>
<%@page import="at.klu.DBConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
DBConn db = new DBConn();
List<Category> categories = db.selectCategories();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bibliothek</title>
	</head>
	<body>
		<h3>Bibliothek</h3>
		<h3>Buch einfügen</h3>
		<form action="InsertServlet" method="post">
			Titel: <input type="text" name="title" placeholder="How 2 Java"><br />
			Autor: <input type="text" name="author" placeholder="Eierkopf"><br />
			ISBN: <input type="text" name="ISBN" placeholder="123-456-789"><br />
			category_id:
			<select name="category_id">
				<% for(Category c : categories){ %>
					<option value="<%= c.id%>"><%= c.name %></option>
				<% } %>
			</select>
			<br />
			<input type="submit" value="abschicken">
		</form>
		<h3>Bücher</h3>
		<% for(Category c : categories) { %>
			<fieldset>
				<legend><%=c.name%></legend>
				<%
				List<Book> books = db.selectBooks(c.id);
				%>
				<table border="1px">
					<tr><td>Titel</td><td>Autor</td><td>ISBN</td><td></td></tr>
					<% for(Book b : books) { %>
						<tr>
							<td><%= b.title %></td>
							<td><%= b.author %></td>
							<td><%= b.ISBN %></td>
							<td><a href="DeleteServlet?id=<%= b.id %>" > &times;</a></td>
						</tr>

					<% } %>
				</table>
			</fieldset>
		<% } %>
	</body>
</html>
