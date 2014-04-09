package genebook.websvr.example.book.service;

import genebook.websvr.example.book.dao.BookRepository;
import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService{
	
    @Resource
    private BookRepository bookRepository;
	
	@Override
	public String search() {
		
		return null;
	}

	@Override
	public TBook add(BookDTO bookDTO) {
		TBook book = new TBook();
		book.setId(bookDTO.getId());
		book.setCreateTime(bookDTO.getCreateTime());
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setPrice(bookDTO.getPrice());
		book.setState(bookDTO.getState());
		
		return bookRepository.save(book);
	}

	@Override
	public String update() {
		
		return null;
	}

	@Override
	public String find() {
		
		return null;
	}

	@Override
	public String delete() {
		
		return null;
	}

	protected void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

}
