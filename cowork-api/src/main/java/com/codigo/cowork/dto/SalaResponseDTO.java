package com.codigo.cowork.dto;

public class SalaResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private Integer capacidad;
    private String ubicacion;
    private Boolean activa;
    private String descripcionCorta;

    public SalaResponseDTO() {
    }

    public SalaResponseDTO(Long id, String codigo, String nombre, Integer capacidad, Boolean activa, String ubicacion, String descripcionCorta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.activa = activa;
        this.ubicacion = ubicacion;
        this.descripcionCorta = descripcionCorta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }
}
