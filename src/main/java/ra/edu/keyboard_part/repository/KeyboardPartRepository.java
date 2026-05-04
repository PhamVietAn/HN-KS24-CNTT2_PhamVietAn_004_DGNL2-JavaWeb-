package ra.edu.keyboard_part.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.model.KeyboardPart;

@Repository
public interface KeyboardPartRepository extends JpaRepository<KeyboardPart, Long> {

    Page<KeyboardPart> findByCategory(ComponentCategory category, Pageable pageable);

    Page<KeyboardPart> findByPartNameContainingIgnoreCase(String partName, Pageable pageable);

    Page<KeyboardPart> findByPartNameContainingIgnoreCaseAndCategory(String partName, ComponentCategory category, Pageable pageable);

    Page<KeyboardPart> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    Page<KeyboardPart> findByPartNameContainingIgnoreCaseAndCategoryAndPriceBetween(
            String partName, ComponentCategory category, Double minPrice, Double maxPrice, Pageable pageable);
}

