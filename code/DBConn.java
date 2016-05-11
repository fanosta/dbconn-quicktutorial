package at.klu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
	
	Connection conn;
	Statement stm;
	
	public DBConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliothek", "root", "");
		stm = conn.createStatement();
	}
	
	public List<Category> selectCategories() throws SQLException{
		List<Category> result = new ArrayList<>();
		
		String sql = "SELECT * from categories";
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()){
			Category c = new Category(rs.getInt("id"), rs.getString("name"));
			
			result.add(c);
		}
		
		
		return result;
	}
	
	public List<Book> selectBooks(int id_categories) throws SQLException{
		List<Book> result = new ArrayList<>();
		
		String sql = "SELECT * FROM books WHERE category_id = " + id_categories;
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			Book b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("ISBN"), rs.getInt("category_id"));
			result.add(b);
		}
		
		return result;
	}
	
	public void insertCategory(String name) throws SQLException{
		String sql = "INSERT INTO categories (name) values (?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		pstm.execute();
	}
	
	public void insertBook(Book b) throws SQLException{
		String sql = "INSERT INTO books (title, author, ISBN, category_id) values (?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, b.title);
		pstm.setString(2, b.author);
		pstm.setString(3, b.isbn);
		pstm.setInt(4, b.category_id);
		pstm.execute();
	}
	
	public void deleteBook(int id) throws SQLException{
		String sql = "DELETE from books where id = " + id;
		stm.execute(sql);
	}
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DBConn db = new DBConn();
		
		// db.insertCategory("Physik");
		
		
		List<Category> categories = db.selectCategories();
		System.out.println(categories);
		
		List<Book> books = db.selectBooks(3);
		System.out.println(books);
		// db.deleteBook(1);
	}
}
