package com.nabhatek.catalogservice.web;

import com.amazonaws.services.s3.model.Bucket;
import com.nabhatek.catalogservice.domain.S3Service;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(S3Controller.class)
public class S3ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private S3Service s3Service;

    @Test
    void testListBuckets() throws Exception {
        given(s3Service.listBuckets()).willReturn(
                List.of(
                        new Bucket("braman-home"),
                        new Bucket("cf-templates-1kamcd01946q5-us-east-1"),
                        new Bucket("dbbtech-access-logs"),
                        new Bucket("dbbtech-contact-form"),
                        new Bucket("dbbtech.com"),
                        new Bucket("elasticbeanstalk-us-west-1-027420182517"),
                        new Bucket("www.dbbtech.com")

                )
        );

        mockMvc.perform(get("/buckets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(7))
                .andExpect(jsonPath("$[0]").value("braman-home"))
                .andExpect(content().json("""
                                [
                                    "braman-home",
                                    "cf-templates-1kamcd01946q5-us-east-1",
                                    "dbbtech-access-logs",
                                    "dbbtech-contact-form",
                                    "dbbtech.com",
                                    "elasticbeanstalk-us-west-1-027420182517",
                                    "www.dbbtech.com"
                                ]                        
                        """,false));
    }
}
