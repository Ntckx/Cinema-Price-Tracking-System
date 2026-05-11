package com.ntck.cinematickettrackingsystem.GlobalException;

import java.util.UUID;

public class MovieRoundNotFoundException extends RuntimeException{
    public MovieRoundNotFoundException(UUID id){
        super("Movie Round not found with id "+ id);
    }
}
