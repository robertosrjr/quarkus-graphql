package com.gmail.robertosrjr.adapter.controller.graphql;

import com.gmail.robertosrjr.application.GalaxyService;
import com.gmail.robertosrjr.domain.Film;
import com.gmail.robertosrjr.domain.Hero;
import org.eclipse.microprofile.graphql.*;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@GraphQLApi
public class FilmResource {

    @Inject
    GalaxyService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    /**
     * Consultas reativas
     * CompletionStage significa que quando uma solicitação contém mais de uma consulta,
     *   elas serão executadas simultaneamente.
     * @param id
     * @return List<Film>
     */
    @Query
    @Description("Get a Films from a galaxy far far away")
    public CompletionStage<Film> getFilm(@Name("filmId") int id) {

        CompletionStage<Film> cf = CompletableFuture.supplyAsync(()-> service.getFilm(id));
        return cf;
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    @Query
    public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }
}