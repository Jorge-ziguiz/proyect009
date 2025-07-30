package es.cic.curso25.proyecto009.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Arbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Version
    private Long version;

    @Column(nullable = false,unique = true)
    private String Nombre;

    private String Familia;

    private String Orden;

    @OneToMany(mappedBy = "arbol" ,fetch = FetchType.LAZY)
    private List<Rama> ramas;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String orden) {
        Orden = orden;
    }

    public List<Rama> getRamas() {
        return ramas;
    }

    public void setRamas(List<Rama> ramas) {
        this.ramas = ramas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
        result = prime * result + ((Familia == null) ? 0 : Familia.hashCode());
        result = prime * result + ((Orden == null) ? 0 : Orden.hashCode());
        result = prime * result + ((ramas == null) ? 0 : ramas.hashCode());
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
        Arbol other = (Arbol) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (Nombre == null) {
            if (other.Nombre != null)
                return false;
        } else if (!Nombre.equals(other.Nombre))
            return false;
        if (Familia == null) {
            if (other.Familia != null)
                return false;
        } else if (!Familia.equals(other.Familia))
            return false;
        if (Orden == null) {
            if (other.Orden != null)
                return false;
        } else if (!Orden.equals(other.Orden))
            return false;
        if (ramas == null) {
            if (other.ramas != null)
                return false;
        } else if (!ramas.equals(other.ramas))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Arbol [Id=" + Id + ", Nombre=" + Nombre + ", Familia=" + Familia + ", Orden=" + Orden + "]";

    }

    


    

  
    




}
