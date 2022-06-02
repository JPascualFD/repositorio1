/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author PC
 */
public class CamisaPlayera {
    private int id;
    private String carrera;
    private int camisaML;
    private int camisaMC;
    private int playera;

    public CamisaPlayera(int id, String carrera, int camisaML, int camisaMC, int playera) {
        this.id = id;
        this.carrera = carrera;
        this.camisaML = camisaML; 
        this.camisaMC = camisaMC; 
        this.playera = playera;
    }
    
    
    public int getCamisaML() {
        return camisaML;
    }

    public void setCamisaML(int camisaML) {
        this.camisaML = camisaML;
    }

    public int getCamisaMC() {
        return camisaMC;
    }

    public void setCamisaMC(int camisaMC) {
        this.camisaMC = camisaMC;
    }

    public int getPlayera() {
        return playera;
    }

    public void setPlayera(int playera) {
        this.playera = playera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
