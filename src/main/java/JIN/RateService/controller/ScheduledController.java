package JIN.RateService.controller;

import JIN.RateService.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@EnableScheduling
@Controller
@RequiredArgsConstructor
public class ScheduledController {

    private final RateService rateService;

    // 30분마다 실행
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void executeTask() {
        rateService.find("findUser");
    }
}
