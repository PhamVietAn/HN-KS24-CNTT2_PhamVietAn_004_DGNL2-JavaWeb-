package ra.edu.keyboard_part.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "KeyboardPart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyboardPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên linh kiện không được để trống.")
    @Size(min=5, max=150,message = "Tên linh kiện phải từ 5 đến 150 ký tự.")
    String partName;

    @NotBlank(message = "Nhà sản xuất không được để trống.")
    String manufacturer;

    @Positive(message = "Giá phải lớn hơn 0.")
    Double price;

    @PastOrPresent(message = "Ngày phát hành không được trong tương lai.")
    LocalDate releaseDate;

    String partImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @NotNull(message = "Vui lòng chọn loại linh kiện.")
    ComponentCategory category;

    Boolean inStock;
}

