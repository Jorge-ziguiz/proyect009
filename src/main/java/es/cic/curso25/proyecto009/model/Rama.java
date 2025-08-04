package es.cic.curso25.proyecto009.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "arbol")
public class Rama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double LongitudEnMetros;

    private String Color;

    private String Forma;

    @ManyToOne(fetch = FetchType.LAZY)
    private Arbol arbol;

    
    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Hoja> hojas;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getLongitudEnMetros() {
        return LongitudEnMetros;
    }

    public void setLongitudEnMetros(Double longitudEnMetros) {
        LongitudEnMetros = longitudEnMetros;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getForma() {
        return Forma;
    }

    public void setForma(String forma) {
        Forma = forma;
    }



    public List<Hoja> getHojas() {
        return hojas;
    }

    public void setHojas(List<Hoja> hojas) {
        this.hojas = hojas;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((LongitudEnMetros == null) ? 0 : LongitudEnMetros.hashCode());
        result = prime * result + ((Color == null) ? 0 : Color.hashCode());
        result = prime * result + ((Forma == null) ? 0 : Forma.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rama other = (Rama) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (LongitudEnMetros == null) {
            if (other.LongitudEnMetros != null)
                return false;
        } else if (!LongitudEnMetros.equals(other.LongitudEnMetros))
            return false;
        if (Color == null) {
            if (other.Color != null)
                return false;
        } else if (!Color.equals(other.Color))
            return false;
        if (Forma == null) {
            if (other.Forma != null)
                return false;
        } else if (!Forma.equals(other.Forma))
            return false;
  
        return true;
    }

    @Override
    public String toString() {
        return "Rama [Id=" + Id + ", LongitudEnMetros=" + LongitudEnMetros + ", Color=" + Color + ", Forma=" + Forma
                + "]";
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

}
