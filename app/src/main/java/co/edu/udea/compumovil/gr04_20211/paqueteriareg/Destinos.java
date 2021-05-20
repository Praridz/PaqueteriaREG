package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

public class Destinos {
    private Double latitud;
    private Double longitud;
    private String codigo;
    private String telefono;

    public Destinos() {
        //super();
    }

    public Destinos(Double latitud, Double longitud, String codigo, String telefono) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
        this.telefono = telefono;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

