package genebook.websvr.example.book.service;

import genebook.websvr.example.book.dao.BookRepository;
import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServiceImpl implements BookService{
	
    @Resource
    private BookRepository bookRepository;
	

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
	public TBook delete(String id) throws BookNotFoundException {
		TBook deleted = this.bookRepository.findOne(id);
		
		if(deleted==null){
			throw new BookNotFoundException();
		}
		this.bookRepository.delete(id);
		return deleted;
	}

	protected void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<TBook> findAll() {
		return this.bookRepository.findAll();
	}

	@Override
	public TBook findOne(String id) {
		
		return this.bookRepository.findOne(id);
	}


    @Transactional(rollbackFor = BookNotFoundException.class)
	@Override
	public TBook update(BookDTO bookDTO) throws BookNotFoundException {
		TBook book = this.bookRepository.findOne(bookDTO.getId());
		if(book == null){
			throw new BookNotFoundException();
		}
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setPrice(bookDTO.getPrice());
		this.bookRepository.saveAndFlush(book);
		return book;
	}

}
