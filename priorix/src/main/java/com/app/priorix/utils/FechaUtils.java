package com.app.priorix.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para formatear fechas y horas.
 */
public class FechaUtils {

    public static String formatearFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaHora.format(formatter);
    }
}
