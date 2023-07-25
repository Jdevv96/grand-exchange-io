package dev.jdevv.service;

import dev.jdevv.model.TaxRate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Component
public class TaxService {

    public final String API_URL = "https://teapi.netlify.app/api/statetax?state=";
    private RestTemplate restTemplate = new RestTemplate();

    public BigDecimal getTaxRate(String stateCode) {
        String url = API_URL + stateCode.toUpperCase();
        try {
            TaxRate taxRate = restTemplate.getForObject(url, TaxRate.class);
            return taxRate.getSalesTax().divide(new BigDecimal(100));
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tax rate could not be found for your state: " + stateCode.toUpperCase());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error retrieving the tax information.", e);
            }
        }
    }


}
