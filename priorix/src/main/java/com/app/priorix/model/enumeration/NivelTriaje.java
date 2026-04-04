package com.app.priorix.model.enumeration;

public enum NivelTriaje {

    NIVEL_1_ROJO(1, "Rojo"),
    NIVEL_2_NARANJA(2, "Naranja"),
    NIVEL_3_AMARILLO(3, "Amarillo"),
    NIVEL_4_AZUL(4, "Azul"),
    NIVEL_5_VERDE(5, "Verde");

    private final int nivel;
    private final String color;

    NivelTriaje(int nivel, String color) {
        this.nivel = nivel;
        this.color = color;
    }

    public int getNivel() { return nivel; }
    public String getColor() { return color; }
}
