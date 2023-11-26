package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    public List<Book> findAll() {
        return List.of(
                new Book("Władca Pierścieni", "J.R.R. Tolkien", "Fantasy"),
                new Book("Hobbit, czyli tam i z powrotem", "J.R.R. Tolkien", "Fantasy"),
                new Book("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "Fantasy"),
                new Book("Duma i uprzedzenie", "Jane Austen", "Romans"),
                new Book("Restauracja na końcu wszechświata", "Douglas Adams", "Fantastyka naukowa"),
                new Book("Zabić drozda", "Harper Lee", "Klasyka"),
                new Book("Żegnaj, i dzięki za ryby", "Douglas Adams", "Fantastyka naukowa"),
                new Book("Całkiem inna historia", "Douglas Adams", "Fantastyka naukowa"),
                new Book("Kolor magii", "Terry Pratchett", "Fantasy"),
                new Book("Fantastyczne światło", "Terry Pratchett", "Fantasy"),
                new Book("Wielki Gatsby", "F. Scott Fitzgerald", "Klasyka"),
                new Book("Mort", "Terry Pratchett", "Fantasy"),
                new Book("Czarodzicielstwo", "Terry Pratchett", "Fantasy"),
                new Book("Rok 1984", "George Orwell", "Dystopia"),
                new Book("Piramidy", "Terry Pratchett", "Fantasy"),
                new Book("Straż! Straż!", "Terry Pratchett", "Fantasy"),
                new Book("Eric", "Terry Pratchett", "Fantasy"),
                new Book("Dziewczyna z tatuażem", "Stieg Larsson", "Thriller"),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "Przygoda"),
                new Book("Zew Cthulhu", "H.P. Lovecraft", "Dystopia")
        );
    }
}
