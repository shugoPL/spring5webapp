package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;

import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AddressRepository addressRepository;
    private final PublisherRepository publisherRepository;


    @Autowired
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            AddressRepository addressRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.addressRepository = addressRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.addBook(ddd);
        ddd.addAuthor(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444");
        rod.addBook(noEJB);
        noEJB.addAuthor(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in bootstrap");
        System.out.println("Number of Books:" + bookRepository.count());

        Publisher pub = new Publisher("Steven");
        Address pubAddress = new Address("Zurawia", "Lodz", "łódzkie", "91-463");
        pub.addAddress(pubAddress);


        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);

        publisherRepository.save(pub);
        addressRepository.save(pubAddress);
        bookRepository.save(ddd);


        System.out.println(pub);
        System.out.println(pubAddress);
        System.out.println("Publisher Number of Books: " + pub.getBooks().size());


    }
}
