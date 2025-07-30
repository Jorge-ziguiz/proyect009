package es.cic.curso25.proyecto009.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Rama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Version
    private Long Version;

    private Double LongitudEnMetros;

    private String Color;

    private String Forma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="arbol_id")
    private Arbol arbol;


    @OneToMany(mappedBy = "rama", fetch = FetchType.LAZY)
    private List<Hoja> hojas;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getVersion() {
        return Version;
    }

    public void setVersion(Long version) {
        Version = version;
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

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((LongitudEnMetros == null) ? 0 : LongitudEnMetros.hashCode());
        result = prime * result + ((Color == null) ? 0 : Color.hashCode());
        result = prime * result + ((Forma == null) ? 0 : Forma.hashCode());
        result = prime * result + ((arbol == null) ? 0 : arbol.hashCode());
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
        if (arbol == null) {
            if (other.arbol != null)
                return false;
        } else if (!arbol.equals(other.arbol))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rama [Id=" + Id + ", LongitudEnMetros=" + LongitudEnMetros + ", Color=" + Color + ", Forma=" + Forma
                + "]";
    }

    
    

}
