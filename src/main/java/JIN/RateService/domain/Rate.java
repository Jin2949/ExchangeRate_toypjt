package JIN.RateService.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Double plus;
    @Column(nullable = false)
    private Double minus;

    @Builder
    public Rate(Long id, String email, Double price, Double plus, Double minus) {
        this.id = id;
        this.email = email;
        this.price = price;
        this.plus = plus;
        this.minus = minus;
    }
}
