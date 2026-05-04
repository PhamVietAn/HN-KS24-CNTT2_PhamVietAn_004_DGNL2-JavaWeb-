package ra.edu.keyboard_part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.repository.ComponentCategoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service quản lý Danh mục Linh kiện Bàn phím
 * Cung cấp các chức năng CRUD cho ComponentCategory
 */
@Service
@Transactional
public class ComponentCategoryService {
    @Autowired
    private ComponentCategoryRepository categoryRepository;

    /**
     * Lưu mới một danh mục linh kiện
     */
    public ComponentCategory save(ComponentCategory category) {
        return categoryRepository.save(category);
    }

    /**
     * Cập nhật thông tin danh mục linh kiện
     */
    public ComponentCategory update(ComponentCategory category) {
        return categoryRepository.save(category);
    }

    /**
     * Xóa danh mục theo ID
     * Tất cả linh kiện trong danh mục cũng sẽ bị xóa (CASCADE)
     */
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    /**
     * Tìm danh mục theo ID
     */
    public Optional<ComponentCategory> findById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Lấy danh sách tất cả các danh mục
     */
    public List<ComponentCategory> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Đếm tổng số danh mục trong database
     */
    public long countAll() {
        return categoryRepository.count();
    }
}

