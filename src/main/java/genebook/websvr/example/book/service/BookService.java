package genebook.websvr.example.book.service;

import java.util.List;

import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

public interface BookService {
	
	public List<TBook> findAll();
	
	public TBook add(BookDTO bookDTO);
	
	public TBook update(BookDTO book) throws BookNotFoundException;
	
	public TBook findOne(String id);
	
	public TBook delete(String id)throws BookNotFoundException;

}
