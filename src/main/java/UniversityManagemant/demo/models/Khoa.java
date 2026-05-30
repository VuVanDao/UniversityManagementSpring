package UniversityManagemant.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Khoa extends AbstractModel {
    String ma_khoa;
    String ten_khoa;

    // @OneToOne
    // @JoinColumn(name = "truong_khoa_id")
    // User truongKhoa;

    @OneToMany(mappedBy = "khoa")
    List<ChuyenNganh> chuyenNganhs;
}