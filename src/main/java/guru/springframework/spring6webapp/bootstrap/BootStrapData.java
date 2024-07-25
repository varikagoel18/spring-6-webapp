package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepositories;
import guru.springframework.spring6webapp.repositories.BookRepositories;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepositories authorRepositories;
    private final BookRepositories bookRepositories;

    public BootStrapData(AuthorRepositories authorRepositories, BookRepositories bookRepositories) {
        this.authorRepositories = authorRepositories;
        this.bookRepositories = bookRepositories;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book nameBook = new Book("DDD", "12346678");
        eric.getBooks().add(nameBook);
        nameBook.getAuthor().add(eric);
        authorRepositories.save(eric);
        bookRepositories.save(nameBook);

        Author rod = new Author("Rod", "johnson");
        Book j2ee = new Book("J2EE devk", "23423424234");

        rod.getBooks().add(j2ee);
        j2ee.getAuthor().add(rod);
        authorRepositories.save(rod);
        bookRepositories.save(j2ee);

        System.out.println("bookRepositories.count() : " + bookRepositories.count());
        System.out.println("authorRepositories.count() : " + authorRepositories.count());
    }
}
