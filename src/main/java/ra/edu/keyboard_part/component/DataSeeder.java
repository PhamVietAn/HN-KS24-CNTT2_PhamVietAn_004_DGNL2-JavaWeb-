package ra.edu.keyboard_part.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ra.edu.keyboard_part.model.ComponentCategory;
import ra.edu.keyboard_part.model.KeyboardPart;
import ra.edu.keyboard_part.service.ComponentCategoryService;
import ra.edu.keyboard_part.service.KeyboardPartService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ComponentCategoryService categoryService;

    @Autowired
    private KeyboardPartService partService;

    @Override
    public void run(String... args) throws Exception {
        if (categoryService.countAll() == 0) {
            seedData();
            System.out.println("Dữ liệu mẫu đã được khởi tạo thành công!");
        } else {
            System.out.println("Database đã có dữ liệu, bỏ qua khởi tạo dữ liệu mẫu.");
        }
    }

    private void seedData() {
        List<ComponentCategory> categories = new ArrayList<>();

        // Danh mục 1: Tactile Switch
        ComponentCategory switchCategory = ComponentCategory.builder()
                .name("Tactile Switch")
                .description("Các switch có cảm giác tactile, phù hợp cho typing và gaming")
                .build();
        categories.add(switchCategory);

        // Danh mục 2: Linear Switch
        ComponentCategory linearCategory = ComponentCategory.builder()
                .name("Linear Switch")
                .description("Các switch tuyến tính, mượt mà cho gaming")
                .build();
        categories.add(linearCategory);

        // Danh mục 3: Case/Kit
        ComponentCategory caseCategory = ComponentCategory.builder()
                .name("Case/Kit")
                .description("Vỏ bàn phím cơ và các kit hoàn chỉnh")
                .build();
        categories.add(caseCategory);

        // Danh mục 4: Keycap
        ComponentCategory keycapCategory = ComponentCategory.builder()
                .name("Keycap")
                .description("Các nắp phím với thiết kế khác nhau")
                .build();
        categories.add(keycapCategory);

        // Lưu tất cả danh mục vào database
        for (ComponentCategory category : categories) {
            categoryService.save(category);
        }

        List<KeyboardPart> parts = new ArrayList<>();

        // TACTILE SWITCHES
        parts.add(KeyboardPart.builder()
                .partName("MMD Holy Panda V3")
                .manufacturer("Drop & Invyr")
                .price(0.80)
                .releaseDate(LocalDate.of(2021, 6, 15))
                .partImage("holy-panda-v3.jpg")
                .category(switchCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Durock POM T2")
                .manufacturer("Durock")
                .price(0.75)
                .releaseDate(LocalDate.of(2021, 3, 10))
                .partImage("durock-pom-t2.jpg")
                .category(switchCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Zealios V2")
                .manufacturer("Zeal PC")
                .price(1.50)
                .releaseDate(LocalDate.of(2020, 9, 20))
                .partImage("zealios-v2.jpg")
                .category(switchCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Boba U4T Tactile")
                .manufacturer("Outemu")
                .price(0.70)
                .releaseDate(LocalDate.of(2022, 1, 5))
                .partImage("boba-u4t.jpg")
                .category(switchCategory)
                .inStock(true)
                .build());

        // LINEAR SWITCHES
        parts.add(KeyboardPart.builder()
                .partName("Gateron Black Ink V2")
                .manufacturer("Gateron")
                .price(0.50)
                .releaseDate(LocalDate.of(2021, 11, 12))
                .partImage("gateron-black-ink.jpg")
                .category(linearCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("NovelKeys Cream")
                .manufacturer("NovelKeys")
                .price(0.60)
                .releaseDate(LocalDate.of(2020, 12, 1))
                .partImage("novelkeys-cream.jpg")
                .category(linearCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Alpaca V2")
                .manufacturer("NovelKeys")
                .price(0.65)
                .releaseDate(LocalDate.of(2022, 4, 18))
                .partImage("alpaca-v2.jpg")
                .category(linearCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Tangerine Switches")
                .manufacturer("C3 Equalz")
                .price(0.55)
                .releaseDate(LocalDate.of(2021, 8, 22))
                .partImage("tangerine-switches.jpg")
                .category(linearCategory)
                .inStock(true)
                .build());

        // CASES/KITS
        parts.add(KeyboardPart.builder()
                .partName("Keychron K2 Aluminum")
                .manufacturer("Keychron")
                .price(99.00)
                .releaseDate(LocalDate.of(2022, 2, 10))
                .partImage("keychron-k2.jpg")
                .category(caseCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("GMK NK65 Case Anodized")
                .manufacturer("GMK/NK")
                .price(85.00)
                .releaseDate(LocalDate.of(2021, 5, 5))
                .partImage("gmk-nk65.jpg")
                .category(caseCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Tofu65 Aluminum")
                .manufacturer("KBDfans")
                .price(110.00)
                .releaseDate(LocalDate.of(2020, 10, 15))
                .partImage("tofu65-alu.jpg")
                .category(caseCategory)
                .inStock(false)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("Rama Koto Pink")
                .manufacturer("Rama Artisan")
                .price(299.00)
                .releaseDate(LocalDate.of(2021, 12, 1))
                .partImage("rama-koto.jpg")
                .category(caseCategory)
                .inStock(true)
                .build());

        // KEYCAPS
        parts.add(KeyboardPart.builder()
                .partName("GMK Striker")
                .manufacturer("GMK")
                .price(135.00)
                .releaseDate(LocalDate.of(2021, 7, 20))
                .partImage("gmk-striker.jpg")
                .category(keycapCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("SA Profile Dreameater")
                .manufacturer("PMK")
                .price(98.00)
                .releaseDate(LocalDate.of(2020, 11, 10))
                .partImage("sa-dreameater.jpg")
                .category(keycapCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("KAT Explosion")
                .manufacturer("Kat")
                .price(125.00)
                .releaseDate(LocalDate.of(2021, 9, 5))
                .partImage("kat-explosion.jpg")
                .category(keycapCategory)
                .inStock(true)
                .build());

        parts.add(KeyboardPart.builder()
                .partName("ePBT PBT Keycaps")
                .manufacturer("ePBT")
                .price(79.00)
                .releaseDate(LocalDate.of(2021, 1, 25))
                .partImage("epbt-keycaps.jpg")
                .category(keycapCategory)
                .inStock(true)
                .build());

        // Lưu tất cả linh kiện vào database
        for (KeyboardPart part : parts) {
            partService.save(part);
        }
    }
}

