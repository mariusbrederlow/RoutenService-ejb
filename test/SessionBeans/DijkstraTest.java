/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import DTO.Graph;
import entities.Kante;
import entities.Knoten;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mariusbrederlow
 */
public class DijkstraTest {
     Graph g = new Graph();
    List<Knoten> knoten = new LinkedList<Knoten>();
    List<Knoten> pfad;
    Collection<Kante> kanten = new LinkedList<Kante>();
    Kante k = new Kante();
    Knoten a = new Knoten(1);
    Knoten b = new Knoten(2);
    Knoten c = new Knoten(3);
    Knoten d = new Knoten(4);
    Dijkstra dij = new Dijkstra();
    public DijkstraTest() {
   
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         a.setName("a");
         b.setName("b");
         c.setName("c");
         d.setName("d");
        
        k.setKnotenid(a);
        k.setZielknoten(b);
        k.setZielknotenid(2);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        k.setKnotenid(a);
        k.setZielknoten(c);
        k.setZielknotenid(3);
        k.setLaenge(2);
        k.setMinProKM(2);
        k.setGewicht(2);
        kanten.add(k);
        
        a.setKantn(kanten);
       
        
        
        k.setKnotenid(b);
        k.setZielknoten(d);
        k.setZielknotenid(4);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        k.setKnotenid(b);
        k.setZielknoten(a);
        k.setZielknotenid(1);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        b.setKantn(kanten);
        
        
        
        k.setKnotenid(c);
        k.setZielknoten(a);
        k.setZielknotenid(1);
        k.setLaenge(2);
        k.setMinProKM(2);
        k.setGewicht(2);
        kanten.add(k);
        
        k.setKnotenid(c);
        k.setZielknoten(d);
        k.setZielknotenid(4);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        c.setKantn(kanten);
        
        
        
        k.setKnotenid(d);
        k.setZielknoten(b);
        k.setZielknotenid(2);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        k.setKnotenid(d);
        k.setZielknoten(c);
        k.setZielknotenid(4);
        k.setLaenge(1);
        k.setMinProKM(1);
        k.setGewicht(1);
        kanten.add(k);
        
        d.setKantn(kanten);
       
        
        
        knoten.add(a);
        knoten.add(b);
        knoten.add(c);
        knoten.add(d);
        
        
        g.setKnotenliste(knoten);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doDijkstra method, of class Dijkstra.
     */
    @Test
    public void testDoDijkstra() throws Exception {
       pfad = dij.doDijkstra(g, "a", "d");
       
       assertNotNull(pfad); 
       assertEquals(d, pfad.get(0));

    }
}
