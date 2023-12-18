package com.example.prolog;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrologQueryExecutor {

    private final String prologFilePath;
    private final BookRepository bookRepository;

    public PrologQueryExecutor(String prologFilePath, BookRepository bookRepository) {
        this.prologFilePath = prologFilePath;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        initializePrologEnvironment();
        loadBooksIntoProlog();
    }

    private void initializePrologEnvironment() {
        Atom atom = new Atom(prologFilePath);
        Term[] args = {atom};
        Query query = new Query("consult", args);
        if (!query.hasSolution()) {
            throw new IllegalStateException("Could not load Prolog file: " + prologFilePath);
        }
    }

    private void loadBooksIntoProlog() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            String fact = formatBookForProlog(book);
            Query query = new Query(fact);
            query.hasSolution();
        }
    }

    private String formatBookForProlog(Book book) {
        return String.format("assertz(book('%s', '%s', '%s')).%n",
                escapePrologString(book.title()),
                escapePrologString(book.author()),
                escapePrologString(book.genre()));
    }

    private String escapePrologString(String input) {
        return input.replace("'", "\\'");
    }

    public List<String> executeQuery(String queryString) {
        Query query = new Query(queryString);
        List<Map<String, Term>> results = new ArrayList<>();

        while (query.hasMoreSolutions()) {
            Map<String, Term> solution = query.nextSolution();
            results.add(solution);
        }

        Term[] recommendedBooksTerms = results.get(0).get("Books").listToTermArray();

        return Arrays.stream(recommendedBooksTerms)
                .map(Term::toString)
                .map(title -> title.replace("'", ""))
                .toList();
    }
}
