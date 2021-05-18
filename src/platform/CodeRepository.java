package platform;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends CrudRepository<CodeInformation, Integer> {
    List<CodeInformation> findAllByOrderByIdDesc();
    List<CodeInformation> findTop10ByOrderByIdDesc();
    Optional<CodeInformation> findById(String id);
}