package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	BordersDAO dao;
	private Map<String,Country> idMap;
	Graph<Country, DefaultEdge> grafo;
	
	public Model() {
		dao= new BordersDAO();
		idMap= new HashMap<>();		
	}
	
	public Graph<Country, DefaultEdge> creaGrafo(int x) {
		grafo= new SimpleGraph<>(DefaultEdge.class);
		dao.getCountryAnno(idMap,x);
		Graphs.addAllVertices(grafo, idMap.values());
		if(dao.getBorders(idMap,x).isEmpty())
			throw new RuntimeException("Non ci sono nazioni con confini per quell'anno");
		for(Border b: dao.getBorders(idMap,x)) {
			Graphs.addEdgeWithVertices(this.grafo,idMap.get(b.getState1ab()) , idMap.get(b.getState2ab()));
		}
		return grafo;
	}
	
	public List<Country> getVertici(){
		List<Country> vertici= new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(vertici);
		return vertici;
	}
	
	public int getComponentiConnesse() {
		if(grafo==null) {
			throw new RuntimeException("grafo inesistente");
		}
		ConnectivityInspector<Country,DefaultEdge> conn= new ConnectivityInspector<Country,DefaultEdge>(grafo);
		return conn.connectedSets().size();
	}

	public List<Country> getVerticiRaggiungibili(Country partenza) {
		if(!idMap.containsKey(partenza.getStateAbb()))
			throw new RuntimeException("Il paese selezionato non è nel grafo");
		List<Country> percorso=new ArrayList<>();
		BreadthFirstIterator<Country, DefaultEdge> it =
				 new BreadthFirstIterator<Country, DefaultEdge>(this.grafo,partenza);
		while(it.hasNext()) {
			percorso.add(it.next());
		}
		return percorso;
	}
	

}
