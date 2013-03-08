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
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peters
 */
@Stateless
public class GraphDTO {

    @PersistenceContext
    private EntityManager em;
    private Kante kante;
    List <Knoten> knotenliste = new ArrayList();
    static final Logger logger = Logger.getLogger("logger");
/*
 * initGraph erstellt ein Abbild des aus der Datenbank gelesenen Graphen im Speicher. Das Abbild entspricht 
 * einer verketteten Liste.
 * Ablauf:
 * Fuer jeden Knoten aus tmp hole die Kantenliste
 * Fuer jede Kante aus der KantenListe hole den Zielknoten und setze ihn
 * Wenn fuer einen Knoten alle Kanten mit den entsprechenden Zielknoten gesetzt schreibe Knoten in Knotenliste
 * Die Variable knotenliste repraesentiert dann den Graphen auf dem gearbeitet wird
 */
     public List <Knoten> initGraph(){
         logger.info("<--- Initialisiere den Graphen --->\n");
        knotenliste.clear();
       List tmp = em.createNamedQuery("Knoten.findAll").getResultList();
       Iterator it = tmp.listIterator();
       Iterator itr;
       while(it.hasNext()){
           
          Knoten k =(Knoten) it.next();
          itr = k.getKanten().iterator();  
          logger.info("Bearbeite Knoten " + k.getName());
          
           while(itr.hasNext()){
              kante = (Kante)itr.next();               
              kante.setZielknoten((Knoten) em.createNamedQuery("Knoten.findById").setParameter("id", kante.getZielknotenid()).getSingleResult());
              logger.info("Setz Zielknoten der Kante nach " + kante.getZielknoten().getName());
         }
           logger.info("Fuege Knoten zur Liste hinzu.");
           knotenliste.add(k);
           
       }
       return knotenliste;
   }
    
    
}
