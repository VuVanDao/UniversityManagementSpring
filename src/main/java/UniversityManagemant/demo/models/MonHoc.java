package UniversityManagemant.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class MonHoc extends AbstractModel {
    String ma_mon_hoc;
    String ten_mon_hoc;
    Integer tin_chi;

    // @OneToMany(mappedBy = "monHoc")
    // List<BangDiem> bangDiems;

    @OneToMany(mappedBy = "monHoc")
    List<NhomHoc> nhomHocs;
}
