package ro.otus.education.url.auth.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ro.otus.education.url.auth.demo.controller.BookController;
import ro.otus.education.url.auth.demo.repo.UserRepository;
import ro.otus.education.url.auth.demo.service.AuthorService;
import ro.otus.education.url.auth.demo.service.BookService;
import ro.otus.education.url.auth.demo.service.CommentService;
import ro.otus.education.url.auth.demo.service.GenreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = BookController.class)
@MockBeans({@MockBean(BookService.class), @MockBean(AuthorService.class), @MockBean(GenreService.class), @MockBean(CommentService.class), @MockBean(UserRepository.class)})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
    @Test
    void shouldReturnStartPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("books"));
    }

    @WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
    @Test
    void shouldAddNewBookGet() throws Exception {
        this.mockMvc.perform(get("/create/book")).andExpect(status().isOk());
    }


}