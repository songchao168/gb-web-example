package genebook.websvr.example.book.controller;

import genebook.websvr.example.book.service.BookService;
import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	
	@Resource
	private BookService bookService;
	
	public String search(){
		
		return null;
	}
	
	@RequestMapping("/book/add")
	public String add(){
		System.out.println("---------------ddd");
		BookDTO bt = new BookDTO();
		bt.setCreateTime(new Timestamp(System.currentTimeMillis()));
		bt.setIsbn("123");
		bt.setName("Spring");
		bt.setPrice(new BigDecimal("11.2"));
		System.out.println("---------------ddd"+bt.getState());
		TBook book = bookService.add(bt);
		System.out.println(book.getId());
		return "";
	}
	
	public String update(){
		return null;
	}
	
	public String find(){
		return null;
	}
	
	public String delete(){
		return null;
	}
}
