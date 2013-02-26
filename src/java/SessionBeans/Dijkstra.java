/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import DTO.Graph;
import entities.Kante;
import entities.Knoten;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author mariusbrederlow
 */
@Stateless
@LocalBean
public class Dijkstra {
    static final Logger logger = Logger.getLogger("logger");
    List <Knoten> pfad = new ArrayList();
    String str_pfad = "";

    public List<Knoten> doDijkstra(Graph g, String standort, String zielort){
        
        Knoten quelle=null;
        Knoten ziel=null;
        Knoten tmp;
        String qname = standort;
        String zname = zielort;
        PriorityQueue<Knoten> knotenSchlange;
        knotenSchlange = new PriorityQueue<Knoten>();
        
        Iterator it = g.getKnotenliste().iterator();
        
        //Suche Quell- und Zielknoten in der Liste
        while(it.hasNext()){
            tmp = (Knoten) it.next();
            if(tmp.getName().equals(qname)){
                quelle = tmp;
                logger.info("Setze Quellknoten --> " + quelle.getName());
            }
            if(tmp.getName().equals(zname)){
                ziel = tmp;
                logger.info("Setze Zielknoten --> " + ziel.getName());
            }
        }
        
        
        quelle.setMinDistanz(0);
        logger.info("Setze Quellknoten --> " + quelle.getName() + " MinDistanz auf 0");
        knotenSchlange.add(quelle);
        logger.info("Fuege Quellknoten der Prio Queue hinzu");
        while(!knotenSchlange.isEmpty()){
             Knoten knoten1 = knotenSchlange.poll();
             logger.info("Nehme hoechst priores Element aus der Prio Queue --> " + knoten1.getName());
             logger.info("Beginne mit Berechnug der Distanzen fuer alle Kanten von " + knoten1.getName());
             for(Kante kante1 : knoten1.getKanten()){ 
                    float laenge = kante1.getLaenge();
                    Knoten knoten2 = kante1.getZielknoten();
                    logger.info("Hole Zielknoten fuer Kante von " + knoten1.getName() + " nach " + knoten2.getName());
                    logger.info("Berechne Gewicht fuer Kante");
                    float gewicht = laenge/10*(kante1.getMinProKM()+kante1.getGewicht());
                    logger.info("Dauer = " + gewicht);
                    float distanzDurchKnoten1 = knoten1.getMinDistanz() + gewicht;
                    logger.info("Setze Distanz von " + knoten1.getName() + " nach " + knoten2.getName() + " auf " + distanzDurchKnoten1);
                    if(distanzDurchKnoten1 < knoten2.getMinDistanz()){
                        logger.info(distanzDurchKnoten1 + " < " + knoten2.getMinDistanz());
                        knotenSchlange.remove(knoten2);       
                        logger.info("Setze " + knoten2.getName() + " MinDistanz auf " + distanzDurchKnoten1);
                        knoten2.setMinDistanz((int)distanzDurchKnoten1);
                        logger.info("Setze Vorgaenger von " + knoten2.getName() + " auf " + knoten1.getName());
                        knoten2.setVorgaenger(knoten1);
                        knotenSchlange.add(knoten2);                        
                    }
                }
             
             
        }
       logger.info("Prio Queue abgearbeitet ---> Erstelle Pfad"); 
       
      
    while(ziel !=null){
        pfad.add(ziel);        
        ziel=ziel.getVorgaenger();
    }
        Collections.reverse(pfad);
      
        return pfad;
    }

}
