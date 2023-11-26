package com.example.book.service;

import com.example.prolog.PrologQueryExecutor;
import org.jpl7.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final PrologQueryExecutor prologQueryExecutor;

    @Autowired
    public BookService(PrologQueryExecutor prologQueryExecutor) {
        this.prologQueryExecutor = prologQueryExecutor;
    }

    public List<String> getRecommendationsForUser(String username) {
        String query = String.format("all_recommendations(%s, Books).", username);
        Map<String, Term> solution = prologQueryExecutor.executeQuery(query).get(0);

        Term[] recommendedBooksTerms = solution.get("Books").listToTermArray();

        return Arrays.stream(recommendedBooksTerms)
                .map(Term::toString)
                .map(title -> title.replace("'", ""))
                .toList();
    }

}
