package ra.edu.keyboard_part.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ComponentCategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên danh mục không được để trống.")
    @Size(min=5, max=100,message = "Tên danh mục phải từ 5 đến 100 ký tự.")
    String name;

    String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<KeyboardPart> keyboardParts;
}
