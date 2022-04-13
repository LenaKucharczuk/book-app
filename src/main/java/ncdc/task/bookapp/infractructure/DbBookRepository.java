package ncdc.task.bookapp.infractructure;

import ncdc.task.bookapp.domain.Book;
import ncdc.task.bookapp.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class DbBookRepository implements BookRepository {

    private final JPABookRepository jpaBookRepository;

    public DbBookRepository(JPABookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public void save(Book book) {
        jpaBookRepository.save(PersistentBook.fromDomain(book));
    }

    @Override
    public List<Book> getAll() {
        return jpaBookRepository.findAll().stream().map(PersistentBook::toDomain).collect(toList());
    }
}
