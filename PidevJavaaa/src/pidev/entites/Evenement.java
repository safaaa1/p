/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author safa
 */
public class Evenement {
  private int idEvent;
  private String nom;
  private String type;
  private Date date;
   private int nbrPlace;
  private String dressCode;
      private String image;

    public Evenement(int idEvent, String nom, String type, Date date, int nbrPlace, String dressCode, String image) {
        this.idEvent = idEvent;
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.nbrPlace = nbrPlace;
        this.dressCode = dressCode;
        this.image = image;
    }

    public Evenement(String nom, String type, Date date, int nbrPlace, String dressCode, String image) {
        this.nom = nom;
        this.type = type;
        this.date = date;
        this.nbrPlace = nbrPlace;
        this.dressCode = dressCode;
        this.image = image;
    }
    
 public Evenement() {
    }

    public Evenement(String string, String string0, int aInt, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int idEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", nom=" + nom + ", type=" + type + ", date=" + date + ", nbrPlace=" + nbrPlace + ", dressCode=" + dressCode + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.idEvent;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.date);
        hash = 11 * hash + this.nbrPlace;
        hash = 11 * hash + Objects.hashCode(this.dressCode);
        hash = 11 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.nbrPlace != other.nbrPlace) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dressCode, other.dressCode)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    
   

}
