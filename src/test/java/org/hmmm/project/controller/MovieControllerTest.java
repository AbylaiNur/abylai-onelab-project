package org.hmmm.project.controller;

import org.hmmm.project.dto.GenreDTO;
import org.hmmm.project.dto.MovieDTO;
import org.hmmm.project.dto.MovieDetailsDTO;
import org.hmmm.project.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void testGetAllMovies() throws Exception {
        Mockito.when(movieService.getAllMovies()).thenReturn(getMovies());

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetMovieDetails() throws Exception {
        Mockito.when(movieService.getMovieDetails(1L)).thenReturn(Optional.ofNullable(getMovieDetails()));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/details/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Movie 1"))
                .andExpect(jsonPath("$.rating").value(9.3))
                .andExpect(jsonPath("$.ratesCount").value(2000))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.comments").isArray())
                .andExpect(jsonPath("$.comments.length()").value(0))
                .andExpect(jsonPath("$.genres").isArray())
                .andExpect(jsonPath("$.genres.length()").value(1));
    }

    @Test
    void testGetMovieDetailsNotFound() throws Exception {
        Mockito.when(movieService.getMovieDetails(Mockito.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/details/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRateMovie() throws Exception {
        Mockito.when(movieService.rateMovie(1L, 1L, 5)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/rate")
                        .param("movieId", "1")
                        .param("userId", "1")
                        .param("rate", "5"))
                .andExpect(status().isOk());
    }

    @Test
    void testRateMovieNotFound() throws Exception {
        Mockito.when(movieService.rateMovie(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/rate")
                        .param("movieId", "1")
                        .param("userId", "1")
                        .param("rate", "5"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteMovie() throws Exception {
        Mockito.when(movieService.deleteMovie(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/delete/{id}", 1L))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/delete/{id}", 2L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchMoviesByTitle() throws Exception {
        Mockito.when(movieService.searchMoviesByTitle("Test")).thenReturn(List.of(new MovieDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search")
                        .param("title", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCommentToMovie() throws Exception {
        Mockito.when(movieService.commentMovie(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/comment")
                        .param("movieId", "1")
                        .param("userId", "1")
                        .param("text", "Great movie!"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCommentToMovieNotFound() throws Exception {
        Mockito.when(movieService.commentMovie(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/comment")
                        .param("movieId", "999")
                        .param("userId", "1")
                        .param("text", "Great movie!"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTopRatedMovies() throws Exception {
        Mockito.when(movieService.getTopRatedMovies(Mockito.anyInt())).thenReturn(getMovies());

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/top")
                        .param("count", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testSearchMoviesByGenre() throws Exception {
        Mockito.when(movieService.getMoviesByGenre("Action")).thenReturn(List.of(new MovieDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/search/genre")
                        .param("genre", "Action"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddGenreToMovieSuccess() throws Exception {
        Mockito.when(movieService.addGenreToMovie(Mockito.anyLong(), Mockito.anyString())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/add/genre")
                        .param("movieId", "1")
                        .param("genre", "Thriller"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddGenreToMovieBadRequest() throws Exception {
        Mockito.when(movieService.addGenreToMovie(Mockito.anyLong(), Mockito.anyString())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/add/genre")
                        .param("movieId", "1")
                        .param("genre", ""))
                .andExpect(status().isBadRequest());
    }

    private List<MovieDTO> getMovies() {
        return List.of(
                new MovieDTO()
                        .setId(1L)
                        .setTitle("Movie 1")
                        .setRating(9.3f)
                        .setRatesCount(2000),
                new MovieDTO()
                        .setId(2L)
                        .setTitle("Movie 2")
                        .setRating(9.2f)
                        .setRatesCount(1500)
        );
    }

    private MovieDetailsDTO getMovieDetails() {
        return new MovieDetailsDTO()
                .setId(1L)
                .setTitle("Movie 1")
                .setRating(9.3f)
                .setRatesCount(2000)
                .setDescription("Description")
                .setComments(List.of())
                .setGenres(List.of(
                        new GenreDTO().setId(1L).setName("Action"))
                );
    }
}
