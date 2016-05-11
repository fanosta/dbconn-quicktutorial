public DBConn() throws ClassNotFoundException, SQLException {
    Driver driver = new com.mysql.jdbc.Driver();
    DriverManager.registerDriver(driver);

    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliothek", "root", "");
    stm = conn.createStatement();
}
