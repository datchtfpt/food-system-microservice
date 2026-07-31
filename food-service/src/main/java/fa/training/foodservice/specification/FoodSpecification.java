package fa.training.foodservice.specification;

import fa.training.foodservice.dto.FoodDTO;
import fa.training.foodservice.entity.Food;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class FoodSpecification {

    public static Specification<Food> getFoodSpecification(String ingredients, String name) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(name != null) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if(ingredients != null) {
                predicates.add(cb.like(cb.lower(root.get("ingredients")), "%" + ingredients.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
}
