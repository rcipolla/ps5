package pkgLibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pkgExceptions.BookException;

public class Book{

	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private Date publish_date;
	private String description;
	private double cost;

	public Book() {

	}
	
	// TESTS ONLY WORK WHEN RUN ON PC! DOESN'T WORK FOR MAC
	
	// write Book constructor that passes in id
	public Book(String id) throws BookException {
		Book answer = GetBook(id);
	}
	
	
	// Write GetBook based on ReadXMLFile
	public static Book GetBook(String id) throws BookException{
		Catalog cat = ReadXMLFile();
		ArrayList<Book> booksbase = cat.getBooks();
		Book answer = null;
		
		for (Book e : booksbase) {
			if (e.getId().equals(id)) {
				answer = e;
			}
		}
		
		if(answer == null) {
			throw new BookException("Book not found. This book can be added.");
		}else {
			return answer;
		}
	}
	
	public static void AddBook(int catalogid, Book addedbook) throws BookException{
		Book answer = GetBook(addedbook.id);
		if (answer == null) {
			Catalog cat = ReadXMLFile();
			cat.setId(catalogid);
			ArrayList<Book> booksbase = cat.getBooks();
			booksbase.add(addedbook);
			cat.setBooks(booksbase);
			WriteXMLFile(cat);
		}else {
			throw new BookException("Book already exists");
		}
	}
	
	
	// need to read xml in getbook method
	
	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
	
	// need write XML to use addbook
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Book(String id, String author, String title, String genre, double price, Date publish_date, String description, double cost)
	{
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;		
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
		this.cost = cost;
	}
	
 

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	@XmlElement
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	@XmlElement
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
