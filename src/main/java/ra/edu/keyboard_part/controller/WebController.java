package ra.edu.keyboard_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.model.KeyboardPart;
import ra.edu.keyboard_part.service.ComponentCategoryService;
import ra.edu.keyboard_part.service.KeyboardPartService;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ComponentCategoryService categoryService;

    @Autowired
    private KeyboardPartService partService;

    // Số lượng linh kiện hiển thị trên mỗi trang
    private static final int PAGE_SIZE = 5;

    @GetMapping("categories")
    public String listCategories(Model model) {
        List<ComponentCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("categories/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new ComponentCategory());
        return "categories/add-edit";
    }

    @PostMapping("categories/save")
    public String saveCategory(@Valid @ModelAttribute("category") ComponentCategory category,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categories/add-edit";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("categories/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Optional<ComponentCategory> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categories/add-edit";
        }
        return "redirect:/categories";
    }

    @PostMapping("categories/update/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @Valid @ModelAttribute("category") ComponentCategory category,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categories/add-edit";
        }
        category.setId(id);
        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("")
    public String listKeyboardParts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long categoryId,
            Model model) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<KeyboardPart> parts;

        // Nếu có tìm kiếm hoặc lọc, thực hiện tìm kiếm
        if ((search != null && !search.isEmpty()) || categoryId != null) {
            ComponentCategory category = categoryId != null ? categoryService.findById(categoryId).orElse(null) : null;
            String searchTerm = search != null ? search : "";
            parts = partService.search(searchTerm, category, pageable);
            model.addAttribute("search", searchTerm);
            model.addAttribute("categoryId", categoryId);
        } else {
            // Nếu không có tìm kiếm, hiển thị tất cả
            parts = partService.findAll(pageable);
        }

        model.addAttribute("parts", parts);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", parts.getTotalPages());

        return "parts/list";
    }

    @GetMapping("parts/add")
    public String addPartForm(Model model) {
        model.addAttribute("part", new KeyboardPart());
        model.addAttribute("categories", categoryService.findAll());
        return "parts/add-edit";
    }

    @PostMapping("parts/save")
    public String savePart(@Valid @ModelAttribute("part") KeyboardPart part,
                          BindingResult result,
                          @RequestParam("file") MultipartFile file,
                          Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "parts/add-edit";
        }

        // Xử lý upload file ảnh nếu có
        if (!file.isEmpty()) {
            String filename = partService.uploadFile(file);
            part.setPartImage(filename);
        }

        partService.save(part);
        return "redirect:/";
    }

    @GetMapping("parts/edit/{id}")
    public String editPartForm(@PathVariable Long id, Model model) {
        Optional<KeyboardPart> part = partService.findById(id);
        if (part.isPresent()) {
            model.addAttribute("part", part.get());
            model.addAttribute("categories", categoryService.findAll());
            return "parts/add-edit";
        }
        return "redirect:/";
    }

    @PostMapping("parts/update/{id}")
    public String updatePart(@PathVariable Long id,
                            @Valid @ModelAttribute("part") KeyboardPart part,
                            BindingResult result,
                            @RequestParam("file") MultipartFile file,
                            Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "parts/add-edit";
        }

        Optional<KeyboardPart> existingPart = partService.findById(id);
        if (existingPart.isPresent()) {
            part.setId(id);

            // Nếu có upload file ảnh mới
            if (!file.isEmpty()) {
                // Xóa file ảnh cũ nếu tồn tại
                if (existingPart.get().getPartImage() != null) {
                    partService.deleteFile(existingPart.get().getPartImage());
                }
                String filename = partService.uploadFile(file);
                part.setPartImage(filename);
            } else {
                // Giữ lại ảnh cũ nếu không upload ảnh mới
                part.setPartImage(existingPart.get().getPartImage());
            }

            partService.update(part);
        }
        return "redirect:/";
    }

    @GetMapping("parts/delete/{id}")
    public String deletePart(@PathVariable Long id) {
        partService.delete(id);
        return "redirect:/";
    }
}

