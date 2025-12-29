package com.example.play.persistence.mapper;

import com.example.play.domain.State;
import org.mapstruct.Named;

public class StateMapper {
    @Named("stringToState")
    public static State stringToState(String estado) {
        if(estado == null) {
            return null;
        }

        return switch (estado.toUpperCase()){
            case "D" -> State.AVAILABLE;
            case "N" -> State.DISABLE;
            default -> null;
        };
    }

    @Named("stateToString")
    public static String stateToString(State state){
      return switch (state){
          case AVAILABLE -> "D";
          case DISABLE -> "N";
          default -> null;
      };
    }
}
