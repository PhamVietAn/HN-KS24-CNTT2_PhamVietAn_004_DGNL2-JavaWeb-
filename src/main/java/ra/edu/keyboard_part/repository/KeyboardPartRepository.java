package ra.edu.keyboard_part.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.model.KeyboardPart;

/**
 * Repository quản lý các thao tác cơ sở dữ liệu với bảng KeyboardPart
 * Cung cấp các phương thức tìm kiếm, lọc và phân trang
 */
@Repository
public interface KeyboardPartRepository extends JpaRepository<KeyboardPart, Long> {
    
    /**
     * Tìm linh kiện theo danh mục (có phân trang)
     */
    Page<KeyboardPart> findByCategory(ComponentCategory category, Pageable pageable);

    /**
     * Tìm kiếm linh kiện theo tên (không phân biệt hoa thường, có phân trang)
     */
    Page<KeyboardPart> findByPartNameContainingIgnoreCase(String partName, Pageable pageable);

    /**
     * Tìm kiếm linh kiện theo tên và danh mục (không phân biệt hoa thường, có phân trang)
     */
    Page<KeyboardPart> findByPartNameContainingIgnoreCaseAndCategory(String partName, ComponentCategory category, Pageable pageable);

    /**
     * Tìm kiếm linh kiện trong khoảng giá (có phân trang)
     */
    Page<KeyboardPart> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    /**
     * Tìm kiếm linh kiện theo tên, danh mục và khoảng giá (có phân trang)
     */
    Page<KeyboardPart> findByPartNameContainingIgnoreCaseAndCategoryAndPriceBetween(
            String partName, ComponentCategory category, Double minPrice, Double maxPrice, Pageable pageable);
}

