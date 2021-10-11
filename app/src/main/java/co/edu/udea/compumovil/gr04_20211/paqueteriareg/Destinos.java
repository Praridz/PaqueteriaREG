package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

public class Destinos {
    private Double latitud;
    private Double longitud;
    private String codigo;
    private String telefono;
    private String correoUser;
    private String estado;

    public Destinos() {
        //super();
    }

    public Destinos(Double latitud, Double longitud, String codigo, String telefono, String correoUser, String estado) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
        this.telefono = telefono;
        this.correoUser = correoUser;
        this.estado = estado;
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

    public String getCorreoUser() {
        return correoUser;
    }

    public void setCorreoUser(String correoUser) {
        this.correoUser = correoUser;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

