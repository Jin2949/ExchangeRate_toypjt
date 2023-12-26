package JIN.RateService.dto;


import JIN.RateService.domain.Rate;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RateDto {
    private Long id;
    private String email;
    private Double price;
    private Double plus;
    private Double minus;

    public Rate toEntity() {
        return Rate.builder()
                .id(id)
                .email(email)
                .price(price)
                .plus(plus)
                .minus(minus)
                .build();
    }
    @Builder
    public RateDto(String email, Double price, Double plus, Double minus) {
        this.id = id;
        this.email = email;
        this.price = price;
        this.plus = plus;
        this.minus = minus;
    }
}
