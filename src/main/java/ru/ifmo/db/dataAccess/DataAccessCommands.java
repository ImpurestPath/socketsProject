package ru.ifmo.db.dataAccess;

import java.io.Serializable;

public enum DataAccessCommands implements Serializable {
    ADD_FILM,
    ADD_FILM_GENRE,
    ADD_FILM_COST,
    ADD_FILM_ACTOR,
    ADD_ACTOR,
    ADD_GENRE,
    ADD_USER,
    ADD_USER_SUBSCRIPTION,
    ADD_USER_FILM,
    ADD_SUBSCRIPTION,
    ADD_SUBSCRIPTION_COST,
    ADD_SUBSCRIPTION_FILM,
    UPDATE_FILM,
    UPDATE_FILM_COST,
    UPDATE_ACTOR,
    UPDATE_GENRE,
    UPDATE_USER,
    UPDATE_SUBSCRIPTION,
    UPDATE_SUBSCRIPTION_COST,
    DELETE_FILM,
    DELETE_FILM_GENRE,
    DELETE_FILM_COST,
    DELETE_FILM_ACTOR,
    DELETE_ACTOR,
    DELETE_GENRE,
    DELETE_USER,
    DELETE_USER_SUBSCRIPTION,
    DELETE_USER_FILM,
    DELETE_SUBSCRIPTION,
    DELETE_SUBSCRIPTION_COST,
    DELETE_SUBSCRIPTION_FILM,
    GET_ALL_FILMS,
    GET_FILM,
    GET_ALL_FILM_COSTS,
    GET_FILM_COST,
    GET_FILM_SUBSCRIPTIONS,
    GET_FILM_ACTORS,
    GET_FILM_GENRES,
    GET_ACTOR,
    GET_GENRE,
    GET_ALL_SUBSCRIPTIONS,
    GET_SUBSCRIPTION,
    GET_ALL_SUBSCRIPTION_COSTS,
    GET_SUBSCRIPTION_COST,
    GET_SUBSCRIPTION_FILMS,
    GET_USER,
    FINISHED,
    ERROR,
    CLOSE_CONNECTION
}