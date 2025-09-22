package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@ConditionalOnProperty(name="polar.testData", havingValue="enabled")
public class BookDataLoader {
    private final BookRepository bookRepository;
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        bookRepository.deleteAll();
        Book book1 = Book.of("1234567890", "Title One", "Author A", 9.90);
        Book book2 = Book.of("0987654321", "Title Two", "Author B", 12.90);

        bookRepository.saveAll(List.of(book1, book2));

    }
}
