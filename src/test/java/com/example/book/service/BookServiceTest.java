package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import com.example.prolog.PrologQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PrologQueryExecutor prologQueryExecutor;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecommendationsForUser() {
        List<Book> books = Arrays.asList(
                new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"),
                new Book("1984", "George Orwell", "Dystopia")
        );
        when(bookRepository.findAll()).thenReturn(books);

        List<String> booksList = List.of("The Hobbit", "1984");
        when(prologQueryExecutor.executeQuery(anyString())).thenReturn(booksList);

        List<String> recommendations = bookService.getRecommendationsForUser("testUser");
        assertEquals(2, recommendations.size());
        assertEquals("The Hobbit", recommendations.get(0));
        assertEquals("1984", recommendations.get(1));
    }
}
