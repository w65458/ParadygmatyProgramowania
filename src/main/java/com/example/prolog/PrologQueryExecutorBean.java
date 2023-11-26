package com.example.prolog;

import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrologQueryExecutorBean extends PrologQueryExecutor {

    public PrologQueryExecutorBean(@Value("${prolog.file.path}") String prologFilePath, BookRepository bookRepository) {
        super(prologFilePath, bookRepository);
    }
}