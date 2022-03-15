package ncdc.task.bookapp.infractructure;

import ncdc.task.bookapp.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class BookRepository {

    private final JPABookRepository jpaBookRepository;

    public BookRepository(JPABookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    public void save(Book book) {
        jpaBookRepository.save(PersistentBook.fromDomain(book));
    }

    public List<Book> getAll() {
        return jpaBookRepository.findAll().stream().map(PersistentBook::toDomain).collect(toList());
    }
}
