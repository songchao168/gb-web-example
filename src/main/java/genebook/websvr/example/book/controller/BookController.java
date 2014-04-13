package genebook.websvr.example.book.controller;

import genebook.websvr.example.book.service.BookNotFoundException;
import genebook.websvr.example.book.service.BookService;
import genebook.websvr.example.dto.BookDTO;
import genebook.websvr.example.uitl.CommonOutput;
import genebook.websvr.example.uitl.InputUtil;
import genebook.websvr.example.uitl.OutputUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/manage/book/list")
	public String search(HttpServletRequest request){
		String jsonBookList = bookService.findAll(InputUtil.formatInputData("json", "", ""));
		CommonOutput<List<BookDTO>> books = OutputUtil.getOutputDataList(BookDTO.class, jsonBookList);
		request.setAttribute("books", books.getData());
//		if(HttpRequestUtil.isMobile(request)){
//			return "";
//		}else{
			return "manage_book_list";
//		}
	}
	
	@RequestMapping(value="/book/add", method = RequestMethod.GET)
	public String addGet(HttpServletRequest request){
		BookDTO book = new BookDTO();
		request.setAttribute("book", book);
//		if(HttpRequestUtil.isMobile(request)){
//			return "";
//		}else{
			return "book_add";
//		}
	}
	
	@RequestMapping(value="/book/add", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("book") BookDTO created,HttpServletRequest request){
		created.setCreateTime(new Timestamp(System.currentTimeMillis()));
		String input = InputUtil.formatInputData("json", "customerId", created);
		String output =  bookService.add(input);
		CommonOutput<BookDTO> cop = OutputUtil.getOutputData(BookDTO.class, output);
		BookDTO book = cop.getData();
		System.out.println(book.getPrice()+"-------------------");
		System.out.println(book.getId());
		return "redirect:/manage/book/list";
	}
	
	@RequestMapping(value="/book/{bookid}/edit", method = RequestMethod.GET)
	public String updateGet(@PathVariable("bookid") String bookid,HttpServletRequest request){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("id", bookid);
		
		String input = InputUtil.formatInputData("json", "customerId", m);
		String output = bookService.findOne(input);
		CommonOutput<BookDTO> cop = OutputUtil.getOutputData(BookDTO.class, output);
		
		BookDTO edited = cop.getData();
		
		request.setAttribute("book", edited);
//		if(HttpRequestUtil.isMobile(request)){
//			return "";
//		}else{
			return "book_edit";
//		}
	}

	@RequestMapping(value="/book/{bookid}/edit", method = RequestMethod.POST)
	public String updatePost(@Valid @ModelAttribute("book") BookDTO updated,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "book_edit";
		}
		
		String input = InputUtil.formatInputData("json", "customerId", updated);
		try {
			bookService.update(input);
//			CommonOutput<BookDTO> cop = OutputUtil.getOutputData(BookDTO.class, output);
//			cop.getData();
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		
//		return "redirect:/book/"+updated.getId();
		return "redirect:/manage/book/list";
	}
	
//	
//	@RequestMapping(value="/book/{bookid}", method = RequestMethod.POST)
//	public String find(@PathVariable("bookid") String bookid,HttpServletRequest request){
//		TBook aBook = bookService.findOne(bookid);
//		BookDTO book = new BookDTO();
//		book.setId(aBook.getId());
//		book.setIsbn(aBook.getIsbn());
//		book.setName(aBook.getName());
//		book.setPrice(aBook.getPrice());
//		return "";
//	}

	@RequestMapping(value="/book/{bookid}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable("bookid") String bookid,HttpServletRequest request){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("id", bookid);
		String input = InputUtil.formatInputData("json", "customerId", m);
		try {
			bookService.delete(input);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:/manage/book/list";
	}
}
