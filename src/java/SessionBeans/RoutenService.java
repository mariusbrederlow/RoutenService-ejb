/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import DTO.Graph;
import DTO.GraphDTO;
import entities.Knoten;
import interfaces.IRoutenService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author peters
 */
@Stateless
@Remote(IRoutenService.class)
@PermitAll
public class RoutenService implements IRoutenService{

   private List <Knoten> knotenliste = new ArrayList();
   @EJB
   private GraphDTO graphDTO;
   @EJB
   private Graph g;
   @EJB
   private Dijkstra dijkstra;
   private String standort;
   private String zielort;
   
   
 
   
    
    @Override
    public List getSchnellsterWeg(String standort, String zielort) {
        g.setKnotenliste(graphDTO.initGraph());
        knotenliste.clear();
        knotenliste=dijkstra.doDijkstra(g,standort,zielort);
      
        return knotenliste;
    }

    @Override
    public void setStandort(String ort) {
        standort = ort;
        System.out.println(standort);

    }

    @Override
    public void setZielort(String ziel) {
        zielort = ziel;
        System.out.println(zielort);
    }

    @Override
    public List<Knoten> getKnotenListe() {
       g.setKnotenliste(graphDTO.initGraph());
       return g.getKnotenliste();
    }

    

}
