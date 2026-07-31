package fa.training.foodservice.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Foods")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "ingredient", length = 500, nullable = false)
    private String ingredient;

    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "status" ,nullable = false)
    private String status;
}
