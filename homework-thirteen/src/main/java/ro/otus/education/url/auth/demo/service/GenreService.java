package ro.otus.education.url.auth.demo.service;

import ro.otus.education.url.auth.demo.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findByName(String genreName);

    List<Genre> findAll();
}
