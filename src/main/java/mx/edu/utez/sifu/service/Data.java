package mx.edu.utez.sifu.service;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("Solicitante")
    private String curp;

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public String toString() {
        return "Data [curp=" + curp + "]";
    }
    
    
}
