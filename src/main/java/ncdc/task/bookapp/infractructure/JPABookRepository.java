package ncdc.task.bookapp.infractructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPABookRepository extends CrudRepository<PersistentBook, String> {
    List<PersistentBook> findAll();
}
