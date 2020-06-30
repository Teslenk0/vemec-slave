package com.vemec.slave.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


// aca metan lo que se pueda reutilizar
// asi no se esta repitiendo
public class Utils {

    public static String ejemplo(String ejemplo) {
        return "Mandaste al ejemplo: " + ejemplo;
    }

    public static ResponseEntity mapErrors(Exception e) {
        if (e.getMessage().equals("Not Found") || e.getMessage().contains("No class com.vemec.api.models.centro.Centro entity with") || e.getMessage() == "No value present" || e.getMessage().contains("No class com.vemec.api.models.ubicacion.Sala entity") || e.getMessage().contains("No class com.vemec.api.models.paciente.Paciente entity with id"))
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.NOT_FOUND, "No se encontró el Recurso"), null, HttpStatus.NOT_FOUND);
        else if (e.getMessage().equals("Bad credentials"))
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrecto"), null, HttpStatus.UNAUTHORIZED);
        else if(e.getMessage().equals("El campo username y password no deben ser vacios"))
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.BAD_REQUEST, "El campo username y password no deben ser vacios"), null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public static Date parseToSqldate (String fecha) throws ParseException {
        SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return new java.util.Date (formato.parse(fecha).getTime());
        }
        catch (Exception e){
            throw e;
        }
    }

}