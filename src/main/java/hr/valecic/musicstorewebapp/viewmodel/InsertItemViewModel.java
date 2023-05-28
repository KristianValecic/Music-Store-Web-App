package hr.valecic.musicstorewebapp.viewmodel;

import hr.valecic.musicstorewebapp.model.Album;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class InsertItemViewModel {
    private String mediatype;
    private Album album;
    private Long amountinstock;
    private String genretype;
    private BigDecimal price;
    private MultipartFile image;
}
