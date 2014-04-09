package genebook.websvr.example.book.service;

import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

public interface BookService {
	
	public String search();
	
	public TBook add(BookDTO bookDTO);
	
	public String update();
	
	public String find();
	
	public String delete();

}
