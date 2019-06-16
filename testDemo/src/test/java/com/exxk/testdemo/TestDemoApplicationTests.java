package com.exxk.testdemo;

import com.exxk.testdemo.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemoApplicationTests {

    @Test
    public void testImageMd5() {
        String url = "http://10.30.5.109/v1/MEGBOX/faces";
        File file = new File("/Users/xuanleung/Downloads/facegroup1000/b_cred/000002.jpg");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body
                    = new LinkedMultiValueMap<>();

            FileSystemResource resource = new FileSystemResource(file);

            body.add("image", resource);
            body.add("description", "das");
            body.add("imageMd5", CommonUtil.imageMD5(file));

            MultiValueMap<String, String> header = new LinkedMultiValueMap();
            header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.MULTIPART_FORM_DATA_VALUE));
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, header);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate
                    .postForEntity(url, requestEntity, String.class);
            System.out.println(response);
        } catch (HttpClientErrorException e) {
            String a = e.getResponseBodyAsString();
            System.out.println(a);
        }
    }


    @Test
    public void testGetDevices() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://10.30.5.109/v1/MEGBOX/devices", String.class);
        System.out.println(response);
    }

}

