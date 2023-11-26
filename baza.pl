% Preferencje użytkowników
likes(adam, 'Fantasy').
likes(sara, 'Romans').
likes(sara, 'Klasyka').
likes(klaudia, 'Klasyka').
likes(klaudia, 'Dystopia').
likes(jakub, 'Thriller').
likes(jakub, 'Fantasy').
likes(anna, 'Fantastyka naukowa').
likes(anna, 'Przygoda').

% Użytkownik lubi danego autora
likes_author(klaudia, 'Margaret Atwood').
likes_author(jakub, 'J.R.R. Tolkien').
likes_author(anna, 'George R.R. Martin').
likes_author(sara, 'Jane Austen').
likes_author(adam, 'Dan Brown').

% Zależności między gatunkami
similar_genre('Fantasy', 'Przygoda').
similar_genre('Dystopia', 'Fantastyka naukowa').
similar_genre('Romans', 'Dramat').
similar_genre('Dramat', 'Klasyka').
similar_genre('Mystery', 'Thriller').
similar_genre('Historical Fiction', 'Klasyka').

% Reguły wnioskowania
recommend(User, Book) :- likes(User, Genre), book(Book, _, Genre).
recommend_similar(User, Book) :- likes(User, LikedGenre), similar_genre(LikedGenre, SimilarGenre), book(Book, _, SimilarGenre).
recommend_author(User, Book) :- likes_author(User, Author), book(Book, Author, _).
recommend_author_genre(User, Book) :- likes_author(User, Author), likes(User, Genre), book(Book, Author, Genre).

% Reguła zwracająca wszystkie rekomendacje dla użytkownika
all_recommendations(User, Books) :-
    findall(Book, recommend(User, Book), Books1),
    findall(Book, recommend_similar(User, Book), Books2),
    findall(Book, recommend_author(User, Book), Books3),
    findall(Book, recommend_author_genre(User, Book), Books4),
    append(Books1, Books2, Temp1),
    append(Temp1, Books3, Temp2),
    append(Temp2, Books4, AllBooksTemp),
    list_to_set(AllBooksTemp, Books).