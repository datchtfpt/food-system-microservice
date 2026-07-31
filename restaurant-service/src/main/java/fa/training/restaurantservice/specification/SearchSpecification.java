package fa.training.restaurantservice.specification;

import org.springframework.data.jpa.domain.Specification;



public class SearchSpecification {

    // Hàm chung để tìm kiếm gần đúng (LIKE %value%) cho kiểu String
    public static <T> Specification<T> contains(String fieldName, String value) {
        return (root, query, cb) -> {
            // Tự động bỏ qua nếu giá trị truyền vào bị null hoặc rỗng
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            return cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
        };
    }

    // Bạn có thể viết thêm các hàm chung khác nếu cần (ví dụ: equals, greaterThan...)
    public static <T> Specification<T> equals(String fieldName, Object value) {
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.equal(root.get(fieldName), value);
        };
    }
}
