package JIN.RateService.controller;

import JIN.RateService.dto.RateDto;
import JIN.RateService.service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("/rate")
@Slf4j
public class RateController {

    private final RateService rateService;

    @GetMapping("")
    public String createForm(Model model) {
        log.info("GET rate createForm controller");
        model.addAttribute("RateForm", new RateForm());
        Double NowPrice = rateService.find("findPrice");
        model.addAttribute("NowPrice", NowPrice);
        return "rate/createRateForm";
    }

    @PostMapping("")
    public String create(@Valid RateForm form, BindingResult result) {
        log.info("POST rate createForm controller");
        if (result.hasErrors()) {
            return "rate/createRateForm";
        }
        RateDto rateDto = new RateDto();
        rateDto.setEmail(form.getEmail());

        Double price = form.getPrice();
        Double plus = price + price * 0.01 * form.getPlus();
        Double minus = price - price * 0.01 * form.getMinus();
        rateDto.setPrice(price);
        rateDto.setPlus(plus);
        rateDto.setMinus(minus);
        rateService.saveUser(rateDto);
        return "redirect:/";
    }
}
