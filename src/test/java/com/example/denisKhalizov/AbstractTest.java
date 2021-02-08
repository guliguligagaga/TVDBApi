package com.example.denisKhalizov;


import com.denisKhalizov.api.TVDBApi;
import com.denisKhalizov.controller.TVDBController;
import com.denisKhalizov.service.ForwardService;
import com.denisKhalizov.service.RestTemplateResponseErrorHandler;
import com.denisKhalizov.service.TokenService;
import com.denisKhalizov.starters.DenisKhalizovApplication;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DenisKhalizovApplication.class)
public abstract class AbstractTest {


    @Value("${url.series}")
    String seriesUrl;
    @Value("${url.search}")
    String searchUrl;
    @Value("${url.login}")
    String tokenUrl;

    @Autowired
    TVDBApi tvdbApi;

    @Autowired
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    @MockBean
    RestTemplate restTemplate;


    @SpyBean
    ForwardService forwardService;
}
