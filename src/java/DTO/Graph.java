/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Knoten;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author mariusbrederlow
 */
@Stateless
@LocalBean
public class Graph {
   
    

    List <Knoten> knotenliste = new ArrayList();
    
    
    

    public List<Knoten> getKnotenliste() {
        return knotenliste;
    }

    public void setKnotenliste(List <Knoten> knotenliste) {
        
        this.knotenliste = knotenliste;
    }
    
    

}
