package ra.edu.keyboard_part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.keyboard_part.model.ComponentCategory;

import java.util.Optional;

/**
 * Repository quản lý các thao tác cơ sở dữ liệu với bảng ComponentCategory
 * Cung cấp các phương thức CRUD và tìm kiếm tùy chỉnh
 */
@Repository
public interface ComponentCategoryRepository extends JpaRepository<ComponentCategory, Long> {
    /**
     * Tìm danh mục theo tên
     */
    Optional<ComponentCategory> findByName(String name);
}

