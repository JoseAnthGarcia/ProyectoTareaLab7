package beans;

import java.math.BigDecimal;

public class distritosB {
    private String nombreFoto;
    private String rutaFoto;
    private String nombreBodega;
    private BigDecimal direccion;

    public distritosB() {
    }


    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public BigDecimal getDireccion() {
        return direccion;
    }

    public void setDireccion(BigDecimal direccion) {
        this.direccion = direccion;
    }
}
