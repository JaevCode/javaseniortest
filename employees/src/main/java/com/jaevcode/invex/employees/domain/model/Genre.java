package com.jaevcode.invex.employees.domain.model;

import lombok.Getter;

@Getter
public enum Genre {
    MAN("Hombre"),
    WOMAN("Mujer");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public static boolean isValidGenre(String genre) {
        for (Genre e : Genre.values()) {
            if (e.getGenre().equals(genre)) return true;
        }
        return false;
    }
}
