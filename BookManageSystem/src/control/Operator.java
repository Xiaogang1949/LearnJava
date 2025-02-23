package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.Database;
import model.Book;

public class Operator {

	public static int clearKey = 12345678;

	public ArrayList<Book> getBookList() {
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 3、通过数据库的连接操作数据库，实现增删改查
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id,bookname,author,price from book");
			while (rs.next()) { // 如果对象中有数据，就会循环打印出来
				String bookname = rs.getString("bookname");
				String author = rs.getString("author");
				float price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID, bookname, author, price);
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return booklist;
	}

	public boolean addBook(String bookname, String author, float price) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into book(bookname,author,price) values('" + bookname + "','" + author + "'," + price
					+ ")";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}

	public boolean deleteBook(int id, String bookname) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if (id != -1) {
				sql = "delete from book where id =" + id;
			} else {
				sql = "delete from book where bookname ='" + bookname + "'";
			}
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}

	public boolean changeBoo(int id, String bookname, String changename) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if (id != -1) {
				sql = "update book set bookname='" + changename + "'" + " where id=" + id;
			} else {
				sql = "update book set bookname='" + changename + "'" + " where bookname='" + bookname + "'";
			}
			stmt.execute(sql);stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}

	public ArrayList<Book> findBoo(int id, String bookname, String author, String dimname, float minprice, float maxprice) {
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if (id != -1) {
				sql = "select id,bookname,author,price from book" + " where id=" + id;
//				System.out.println(sql);
			}
			else if(bookname != null) {
				sql = "select id,bookname,author,price from book"+" where bookname='"+bookname+"'";
			}
			else if(author != null) {
				sql = "select id,bookname,author,price from book"+" where author='"+author+"'";
			}
			else if(dimname != null) {
				sql = "select id,bookname,author,price from book"+" where bookname like'%"+dimname+"%'";
			}
			else if(maxprice != 0) {
				sql = "select id,bookname,author,price from book where price>="+minprice+" and price<="+maxprice;
			}
			else {
				System.out.println("出现未知错误，请联系管理员！");
				sql = "";
			}
			rs = stmt.executeQuery(sql);
			while (rs.next()) { // 如果对象中有数据，就会循环打印出来
				String bookName = rs.getString("bookname");
				String Author = rs.getString("author");
				float Price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID,bookName,Author,Price);
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt);
		}
		return booklist;
	}

	public ArrayList<Book> printAllBook() {
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id,bookname,author,price from book");
			while (rs.next()) { // 如果对象中有数据，就会循环打印出来
				String bookName = rs.getString("bookname");
				String Author = rs.getString("author");
				float Price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID,bookName,Author,Price);
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return booklist;
	}

	public boolean clearBook() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "truncate table book";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
}
