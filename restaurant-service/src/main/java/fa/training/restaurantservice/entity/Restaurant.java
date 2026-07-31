package fa.training.restaurantservice.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "owner_name", nullable = false, length = 100)
    private String owner;

    private Integer priceFrom;   // DB: price_from (nullable)

    private Integer priceTo;     // DB: price_to (nullable)

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false)
    private LocalDate openDate;  // DB: open_date

    @Column(nullable = false, length = 10)
    private String status;

    @Column(nullable = false)
    private Integer categoryId;  // DB: category_id

}
