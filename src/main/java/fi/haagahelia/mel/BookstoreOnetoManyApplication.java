package fi.haagahelia.mel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.mel.domain.Bookstore;
import fi.haagahelia.mel.domain.BookstoreRepository;
import fi.haagahelia.mel.domain.Category;
import fi.haagahelia.mel.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreOnetoManyApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreOnetoManyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreOnetoManyApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository brepository , CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of info");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Comedy"));
			crepository.save(new Category("Drama"));
			
			brepository.save(new Bookstore("Harry Potter", "Dave" , 1986, 14986 , 40,crepository.findByName("Fantasy").get(0)));
			brepository.save(new Bookstore("Golden Compass", "Jim", 2006, 12889 , 50, crepository.findByName("Drama").get(0)));
			brepository.save(new Bookstore("The Alchemist", "Barry", 1994, 48692 , 30, crepository.findByName("Comedy").get(0)));
			
			log.info("fetch all Books");
			for (Bookstore bookstore : brepository.findAll()) {
				log.info(bookstore.toString());
			}

		};
	}
}
