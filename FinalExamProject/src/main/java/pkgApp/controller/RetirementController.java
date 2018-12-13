package pkgApp.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import pkgApp.RetirementApp;
import javafx.scene.control.Label;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private Label lblSaveEachMonth;

	@FXML
	private TextField txtAnnualReturnWorking;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private Label lblWhatYouNeedToSave;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	
	@FXML
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;

	private HashMap<TextField, String> hmTextField = new HashMap<TextField, String>();

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hmTextField.put(txtYearsToWork, "\\d*?");
		hmTextField.put(txtAnnualReturnWorking, "\\d*(\\.\\d*)?");
		hmTextField.put(txtYearsRetired,"\\d*?");
		hmTextField.put(txtAnnualReturnRetired,"\\d*(\\.\\d*)?");
		hmTextField.put(txtRequiredIncome,"\\d*?");
		hmTextField.put(txtMonthlySSI,"\\d*?");
		
		Iterator it = hmTextField.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();

			txtField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					if (!newPropertyValue) {
						if (!txtField.getText().matches(strRegEx)) {
							txtField.setText("");
							txtField.requestFocus();
						}
					}
				}
			});
		}
		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
		
		lblSaveEachMonth.setText("");
		lblWhatYouNeedToSave.setText("");
		
		Iterator it = hmTextFieldRegEx.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();
			txtField.clear();
		}
		
		//	TODO: Clear all the text inputs
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		pkgCore.Retirement people = new pkgCore.Retirement();
		people.setdAnnualReturnWorking(Double.parseDouble(txtAnnualReturnWorking.getText()));
		people.setdAnnualReturnRetired(Double.parseDouble(txtAnnualReturnRetired.getText()));
		people.setdMonthlySSI(Double.parseDouble(txtMonthlySSI.getText()));
		people.setdRequiredIncome(Double.parseDouble(txtRequiredIncome.getText()));
		people.setiYearsRetired(Integer.parseInt(txtYearsRetired.getText()));
		people.setiYearsToWork(Integer.parseInt(txtYearsToWork.getText()));
		lblWhatYouNeedToSave.setText(""+people.TotalAmountSaved());
		lblSaveEachMonth.setText(""+people.AmountToSave());
		
	}
	
}
