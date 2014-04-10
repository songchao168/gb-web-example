package genebook.websvr.example.book.controller;

import genebook.websvr.example.book.service.BookNotFoundException;
import genebook.websvr.example.book.service.BookService;
import genebook.websvr.example.domain.TBook;
import genebook.websvr.example.dto.BookDTO;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bgi.commons.web.utils.HttpRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	@Resource
	private BookService bookService;
	
	@RequestMapping("/manage/book/list")
	public String search(HttpServletRequest request){
		
		List<TBook> books = bookService.findAll();
		request.setAttribute("books", books);
		if(HttpRequestUtil.isMobile(request)){
			return "";
		}else{
			return "manage_book_list";
		}
	}
	
	@RequestMapping(value="/book/add", method = RequestMethod.GET)
	public String addGet(HttpServletRequest request){
		BookDTO book = new BookDTO();
		request.setAttribute("book", book);
		if(HttpRequestUtil.isMobile(request)){
			return "";
		}else{
			return "book_add";
		}
	}
	
	@RequestMapping(value="/book/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("book") BookDTO created,HttpServletRequest request){
		created.setCreateTime(new Timestamp(System.currentTimeMillis()));
		TBook book = bookService.add(created);
		System.out.println(book.getPrice()+"-------------------");
		System.out.println(book.getId());
		return "redirect:/manage/book/list";
	}
	@RequestMapping(value="/book/{bookid}/edit", method = RequestMethod.GET)
	public String updateGet(@PathVariable("bookid") String bookid,HttpServletRequest request){
		TBook edited = bookService.findOne(bookid);
		//parse TBook to BookDTO
		BookDTO book = new BookDTO();
		book.setId(edited.getId());
		book.setIsbn(edited.getIsbn());
		book.setName(edited.getName());
		book.setPrice(edited.getPrice());
		
		request.setAttribute("book", book);
		if(HttpRequestUtil.isMobile(request)){
			return "";
		}else{
			return "book_edit";
		}
	}
	
	@RequestMapping(value="/book/{bookid}/edit", method = RequestMethod.POST)
	public String updatePost(@Valid @ModelAttribute("book") BookDTO updated,BindingResult bindingResult){
//		TBook book = null;
		if(bindingResult.hasErrors()){
			return "book_edit";
		}
		
		try {
			bookService.update(updated);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		
//		return "redirect:/book/"+updated.getId();
		return "redirect:/manage/book/list";
	}
	
	@RequestMapping(value="/book/{bookid}", method = RequestMethod.POST)
	public String find(@PathVariable("bookid") String bookid,HttpServletRequest request){
		TBook aBook = bookService.findOne(bookid);
		BookDTO book = new BookDTO();
		book.setId(aBook.getId());
		book.setIsbn(aBook.getIsbn());
		book.setName(aBook.getName());
		book.setPrice(aBook.getPrice());
		return "";
	}
	
	@RequestMapping(value="/book/{bookid}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable("bookid") String bookid,HttpServletRequest request){
		try {
			bookService.delete(bookid);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:/manage/book/list";
	}
}
