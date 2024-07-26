package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepositories;
import guru.springframework.spring6webapp.repositories.BookRepositories;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepositories authorRepositories;
    private final BookRepositories bookRepositories;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepositories authorRepositories, BookRepositories bookRepositories, PublisherRepository publisherRepository) {
        this.authorRepositories = authorRepositories;
        this.bookRepositories = bookRepositories;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args)  {
        Author eric = new Author("Eric", "Evans");
        Book nameBook = new Book("DDD", "12346678");
        Publisher nameBookPublisher = new Publisher("Faltu Edison ","line 1 adress, bangalore, KA, 560087");

        updateRepositories(eric, nameBookPublisher, nameBook);

        Author rod = new Author("Rod", "johnson");
        Book j2ee = new Book("J2EE devk", "23423424234");
        Publisher j2eePublisher = new Publisher("Thomson Edison", "line 1 adress, bangalore, KA, 560087");

        updateRepositories(rod, j2eePublisher, j2ee);

        System.out.println("bookRepositories.count() : " + bookRepositories.count());
        System.out.println("authorRepositories.count() : " + authorRepositories.count());
        System.out.println("publisherRepository.count() : " + publisherRepository.count());
    }

    private void updateRepositories(Author author, Publisher publisher, Book book) {
        author.getBooks().add(book);
        book.getAuthor().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);
        authorRepositories.save(author);
        publisherRepository.save(publisher);
        bookRepositories.save(book);
    }
}
