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
