package com.spotify.userprofile.controller;

import com.spotify.userprofile.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("call/consumer")
@Slf4j
public class ConsumerController
{
    @PostMapping(value="/login")
    public ResponseEntity<Object> consumeLogin(@RequestBody UserDto userdto)
    {
        String baseUrl ="http://localhost:8083/auth/v1/login";

        log.info(userdto+"----");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String,String>> result =null;
        try
        {

            result=restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(userdto), new ParameterizedTypeReference<Map<String,String>>(){});
           log.info(result.getBody().toString());
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Login was not successful" , HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);

    }




    private static HttpEntity<UserDto> getHeaders(UserDto userdto)
    {
        HttpHeaders header = new HttpHeaders();

        header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(userdto, header);
    }

}





