package fi.haagahelia.mel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.mel.domain.Bookstore;
import fi.haagahelia.mel.domain.BookstoreRepository;
import fi.haagahelia.mel.domain.CategoryRepository;
@Controller
public class BookstoreController {
	@Autowired
	private BookstoreRepository repository; 
	
	@Autowired
	private CategoryRepository crepository; 
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Bookstore());
    	model.addAttribute("category", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Bookstore book){
        repository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletebook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
        return "redirect:../booklist";
    }     
}
