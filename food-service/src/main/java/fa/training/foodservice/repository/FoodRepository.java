package fa.training.foodservice.repository;


import fa.training.foodservice.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer>, JpaSpecificationExecutor<Food> {
}

// foodrepository co nen su dung dto chua hay la de service
