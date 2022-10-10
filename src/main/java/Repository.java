import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Model, Long> {
    List<Model> findByPublished(boolean published);

    List<Model> findByTitleContaining(String title);
}