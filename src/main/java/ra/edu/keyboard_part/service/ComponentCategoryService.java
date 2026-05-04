package ra.edu.keyboard_part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.repository.ComponentCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComponentCategoryService {
    @Autowired
    private ComponentCategoryRepository categoryRepository;

    public ComponentCategory save(ComponentCategory category) {
        return categoryRepository.save(category);
    }

    public ComponentCategory update(ComponentCategory category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<ComponentCategory> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<ComponentCategory> findAll() {
        return categoryRepository.findAll();
    }

    public long countAll() {
        return categoryRepository.count();
    }
}

