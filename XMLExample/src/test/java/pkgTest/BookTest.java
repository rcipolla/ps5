package pkgTest;

import java.util.Calendar;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import pkgExceptions.BookException;
import pkgLibrary.Book;

public class BookTest {
	
	@Test(expected = BookException.class)
	public void getTest1() throws BookException{
		Book test1 = Book.GetBook("bk1");

	}
	
	@Test
	public void getTest2() throws BookException{
		Book test2 = Book.GetBook("bk109");

	}
	
	@Test(expected = BookException.class)
	public void addTest() throws BookException{
		Book book = new Book();
		book.setAuthor("Rob Cipolla");
		book.setCost(80.00);
		book.setDescription("A book");
		book.setGenre("Autobiography");
		book.setId("bk1000");
		book.setPrice(100.00);
		Calendar t5 = GregorianCalendar.getInstance();
		t5.set(1996,Calendar.JUNE,21);
		Date date5 = t5.getTime();
		book.setPublish_date(date5);
		book.setTitle("The Story of Rob Cipolla");
		Book.AddBook(1, book);
	}

}
