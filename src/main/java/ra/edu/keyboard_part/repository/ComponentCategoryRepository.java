package ra.edu.keyboard_part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.keyboard_part.model.ComponentCategory;

import java.util.Optional;

@Repository
public interface ComponentCategoryRepository extends JpaRepository<ComponentCategory, Long> {

    Optional<ComponentCategory> findByName(String name);
}

