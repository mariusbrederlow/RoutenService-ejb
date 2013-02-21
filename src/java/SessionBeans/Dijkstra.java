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
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author mariusbrederlow
 */
@Stateless
@LocalBean
public class Dijkstra {
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
            }
            if(tmp.getName().equals(zname)){
                ziel = tmp;
            }
        }
        
        
        quelle.setMinDistanz(0);
        knotenSchlange.add(quelle);
        
        while(!knotenSchlange.isEmpty()){
             Knoten knoten1 = knotenSchlange.poll();
             
             
             for(Kante kante1 : knoten1.getKanten()){
                    
                    Knoten knoten2 = kante1.getZielknoten();
                    int gewicht = kante1.getLaenge()*(kante1.getMinProKM()+kante1.getGewicht());
                    int distanzDurchKnoten1 = knoten1.getMinDistanz() + gewicht;
                    
                    if(distanzDurchKnoten1 < knoten2.getMinDistanz()){
                        knotenSchlange.remove(knoten2);
                        
                        knoten2.setMinDistanz(distanzDurchKnoten1);
                        knoten2.setVorgaenger(knoten1);
                        knotenSchlange.add(knoten2);
                        
                    }
                }
             
             
        }
        
       
      
    while(ziel !=null){
        pfad.add(ziel);        
        ziel=ziel.getVorgaenger();
    }
        Collections.reverse(pfad);
      
        return pfad;
    }

}
