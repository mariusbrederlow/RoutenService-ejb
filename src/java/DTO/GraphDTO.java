/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Kante;
import entities.Knoten;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mariusbrederlow
 */
@Stateless
public class GraphDTO {

    @PersistenceContext
    private EntityManager em;
    private Kante kante;
    List <Knoten> knotenliste = new ArrayList();
   

     public List <Knoten> initGraph(){
        knotenliste.clear();
       List tmp = em.createNamedQuery("Knoten.findAll").getResultList();
       Iterator it = tmp.listIterator();
       Iterator itr;
       while(it.hasNext()){
           
          Knoten k =(Knoten) it.next();
          itr = k.getKanten().iterator();  
          
          
           while(itr.hasNext()){
              kante = (Kante)itr.next();              
              kante.setZielknoten((Knoten) em.createNamedQuery("Knoten.findById").setParameter("id", kante.getZielknotenid()).getSingleResult());
         }
           knotenliste.add(k);
           
       }
       return knotenliste;
   }
    
    
}
