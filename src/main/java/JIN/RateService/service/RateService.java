package JIN.RateService.service;

import JIN.RateService.dto.RateDto;
import JIN.RateService.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    static Double nowPrice;


    @Transactional
    public Long saveUser(RateDto rateDto) {
        return rateRepository.save(rateDto.toEntity()).getId();
    }

    public Double find(String param) {
        fastAPI(param);
        return nowPrice;
    }

    private static void fastAPI(String param) {
        try {
            // URL 설정
            String apiUrl = "http://localhost:8000";
            URL url = new URL(apiUrl + "/" + param);

            // 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 응답 읽기
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(inputLine);
                    if (param == "findPrice") {
                        nowPrice = Double.parseDouble(jsonObject.get("price").toString());
                    } else if (param == "findUser") {
                        System.out.println("문자보냈음");
                    }
                }
                in.close();
                // 응답 출력
                System.out.println(response);
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}