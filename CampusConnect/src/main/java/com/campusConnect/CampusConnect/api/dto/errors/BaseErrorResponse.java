package com.campusConnect.CampusConnect.api.dto.errors;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder //esta clase sera heredada por otra
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse implements Serializable { //Serializable permite responder errores con metodos HTTP
    private Integer code;
    private String status; 
}
