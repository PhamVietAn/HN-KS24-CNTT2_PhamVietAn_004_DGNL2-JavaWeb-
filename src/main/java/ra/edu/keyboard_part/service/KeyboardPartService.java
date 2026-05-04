package ra.edu.keyboard_part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.model.KeyboardPart;
import ra.edu.keyboard_part.repository.KeyboardPartRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class KeyboardPartService {
    @Autowired
    private KeyboardPartRepository partRepository;

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    public KeyboardPart save(KeyboardPart part) {
        return partRepository.save(part);
    }

    public KeyboardPart update(KeyboardPart part) {
        return partRepository.save(part);
    }

    public void delete(Long id) {
        Optional<KeyboardPart> part = partRepository.findById(id);
        if (part.isPresent()) {
            String imagePath = part.get().getPartImage();
            // Nếu có ảnh, xóa file ảnh trước khi xóa record
            if (imagePath != null && !imagePath.isEmpty()) {
                deleteFile(imagePath);
            }
            partRepository.deleteById(id);
        }
    }

    public Optional<KeyboardPart> findById(Long id) {
        return partRepository.findById(id);
    }

    public Page<KeyboardPart> findAll(Pageable pageable) {
        return partRepository.findAll(pageable);
    }

    public Page<KeyboardPart> findByCategory(ComponentCategory category, Pageable pageable) {
        return partRepository.findByCategory(category, pageable);
    }

    public Page<KeyboardPart> search(String partName, ComponentCategory category, Pageable pageable) {
        if (category != null) {
            // Tìm theo cả tên và danh mục
            return partRepository.findByPartNameContainingIgnoreCaseAndCategory(partName, category, pageable);
        } else {
            // Chỉ tìm theo tên
            return partRepository.findByPartNameContainingIgnoreCase(partName, pageable);
        }
    }

    public long countAll() {
        return partRepository.count();
    }


    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        // Tạo thư mục upload nếu chưa tồn tại
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Tạo tên file duy nhất bằng UUID để tránh trùng lặp
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // Lưu file vào đĩa
        Path filePath = Paths.get(uploadDir, newFilename);
        Files.write(filePath, file.getBytes());

        return newFilename;
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Ghi lỗi nhưng không ném exception để không ảnh hưởng đến tiến trình xóa record
            e.printStackTrace();
        }
    }
}

