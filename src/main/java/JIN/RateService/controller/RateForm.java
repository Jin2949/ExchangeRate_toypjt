package JIN.RateService.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateForm {

    private Long id;
    private String email;
    private Double price;
    private Double plus;
    private Double minus;

}
