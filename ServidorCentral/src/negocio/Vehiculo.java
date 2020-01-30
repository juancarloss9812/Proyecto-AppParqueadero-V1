package negocio;

public class Vehiculo {
    private Integer perIdentificacion;
    private String vehPlaca;
    private String vehMarca;
    private String vehTipo;
    
    public Vehiculo(){
        
    }
    
    public Vehiculo(Integer id, String vehPlaca, String vehMarca, String vehTipo) {
        this.perIdentificacion = id;
        this.vehPlaca = vehPlaca;
        this.vehMarca = vehMarca;
        this.vehTipo = vehTipo;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getVehMarca() {
        return vehMarca;
    }

    public void setVehMarca(String vehMarca) {
        this.vehMarca = vehMarca;
    }

    public String getVehTipo() {
        return vehTipo;
    }

    public void setVehTipo(String vehTipo) {
        this.vehTipo = vehTipo;
    }

    public Integer getId() {
        return perIdentificacion;
    }

    public void setId(Integer id) {
        this.perIdentificacion = id;
    }
    
}
