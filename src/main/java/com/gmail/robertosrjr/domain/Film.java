package com.gmail.robertosrjr.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Film {

    public String title;
    public Integer episodeID;
    public String director;
    public LocalDate releaseDate;
}
