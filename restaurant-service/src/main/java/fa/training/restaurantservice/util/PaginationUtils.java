package fa.training.restaurantservice.util;

import fa.training.restaurantservice.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationUtils {

    // Hàm này nhận vào một Page của Spring Data và danh sách DTO đã được map
    public static <T, D> PageDTO buildPageDTO(Page<T> page, List<D> dtoList) {
        return PageDTO.builder()
                .size(page.getSize())
                .first(page.isFirst())
                .last(page.isLast())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(dtoList) // Gán danh sách DTO vào đây
                .build();
    }
}
