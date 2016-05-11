package at.klu;

public class Book {
	public int id;
	public String title;
	public String author;
	public String isbn;
	public int category_id;

	public Book(int id, String title, String author, String isbn, int category_id) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", category_id=" + category_id + "]";
	}
}
