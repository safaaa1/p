/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author safa
 */
public class Reservation {
    private int idReser;
  private Date dateReservation; 
  private String etat;
      private int idEvent;
    private int idParent;

    public Reservation(int idReser, Date dateReservation, String etat, int idEvent, int id) {
        this.idReser = idReser;
        this.dateReservation = dateReservation;
        this.etat = etat;
        this.idEvent = idEvent;
        this.idParent = idParent;
    }

    public Reservation(int idEvent, int idParent) {
        this.idEvent = idEvent;
        this.idParent = idParent;
    }

    public Reservation() {
    }

    public int getIdReser() {
        return idReser;
    }

    public void setIdReser(int idReser) {
        this.idReser = idReser;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.idReser;
        hash = 13 * hash + Objects.hashCode(this.dateReservation);
        hash = 13 * hash + Objects.hashCode(this.etat);
        hash = 13 * hash + this.idEvent;
        hash = 13 * hash + this.idParent;
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
        final Reservation other = (Reservation) obj;
        if (this.idReser != other.idReser) {
            return false;
        }
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.idParent != other.idParent) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.dateReservation, other.dateReservation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReser=" + idReser + ", dateReservation=" + dateReservation + ", etat=" + etat + ", idEvent=" + idEvent + ", idParent=" + idParent + '}';
    }

    

}
