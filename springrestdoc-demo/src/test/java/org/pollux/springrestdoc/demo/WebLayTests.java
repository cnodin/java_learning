package org.pollux.springrestdoc.demo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pollux.springrestdoc.demo.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 07/12/2017
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebLayTests {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello world")))
//                .andDo(document("/home"));

        this.mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello world")))
               ;

//                .andDo(document("index-example",
//                        links(
//                                linkWithRel("notes").description("The <<resources-notes,Notes resource>>"),
//                                linkWithRel("tags").description("The <<resources-tags,Tags resource>>"),
//                                linkWithRel("profile").description("The ALPS profile for the service")),
//                        responseFields(
//                                subsectionWithPath("_links").description("<<resources-index-links,Links>> to other resources"))));
    }

}
