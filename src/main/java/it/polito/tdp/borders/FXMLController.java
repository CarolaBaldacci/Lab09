
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	Graph<Country, DefaultEdge> grafo;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    private ComboBox<Country> cmbBox;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	cmbBox.getItems().clear();
    	int anno=Integer.parseInt(txtAnno.getText()) ;
    	if(anno<1816 || anno>2016 || txtAnno.getText()==null) {
    		txtResult.setText("inserire un anno tra il 1816 e il 2016");
    		return;
    	}
    	grafo=this.model.creaGrafo(anno);
    	txtResult.appendText("Numero di componenti connesse del grafo= "+this.model.getComponentiConnesse()+"\n");
    	List<Country> listaVertici= this.model.getVertici();
    	for(Country c:listaVertici) {
    		txtResult.appendText("Il paese: "+ c.getStateName()+" ha "+grafo.degreeOf(c)+" confini \n");
    		cmbBox.getItems().add(c);
    	}  	
    }

    @FXML
    void doRaggiungiStati(ActionEvent event) {
    	if(grafo==null) {
    		txtResult.appendText("calcolare prima i confini nell'anno inserito");
    		return;
    	}
    	Country partenza= cmbBox.getValue();
    	if(partenza==null) {
    		txtResult.appendText("Selezionare un paese da cui partire ");
    	}
    	List<Country> verticiRaggiungibili= this.model.getVerticiRaggiungibili(partenza);
    	txtResult.appendText("Stati raggiungibili da "+partenza.getStateName()+": \n");
    	for(Country c:verticiRaggiungibili)
    		txtResult.appendText(c.getStateName()+" \n");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
