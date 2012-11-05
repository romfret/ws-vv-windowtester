package test;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;

public class Tests extends
		UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public Tests() {
		super(com.sun.demo.addressbook.AddressFrame.class);
	}

	/**
	 * Main test method.
	 */
	public void testAllTextFieldsShouldBeDisabledAtApplicationStartup()
			throws Exception {
		IUIContext ui = getUI();
	
		ui.assertThat(new LabeledTextLocator("Last Name").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Phone").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Address 1").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Address 2").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("City").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Country").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("First Name").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Middle Name").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Email").isEnabled(false));
		
		//Avant
		//Auto généré par l'inspecteur de WindowTester Pro
		//Ne marche pas à l'éxecution des tests
		//ui.assertThat(new JTextComponentLocator(JTextField.class, 3, new SwingWidgetLocator("AddressPanel")).isEnabled(false));
		//ui.assertThat(new JTextComponentLocator(JTextField.class, 4, new SwingWidgetLocator("AddressPanel")).isEnabled(false));
		
		//Les tests après modification en LabeledTextLocator
		ui.assertThat(new  LabeledTextLocator("ZIP").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("State").isEnabled(false));


	}
	
	public void testAllTextFieldsShouldBeEnabledAfterClickingTheNewButton()
	throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.assertThat(new LabeledTextLocator("Last Name").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Phone").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Address 1").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Address 2").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("City").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("State").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Country").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("First Name").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Middle Name").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("Email").isEnabled(true));
		ui.assertThat(new LabeledTextLocator("ZIP").isEnabled(true));
	}
	
	public void testTheButtonForDeleteShouldBeEnabledWhereThereIsASelectedItemInTheList() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText("toto");
		ui.click(new JButtonLocator("Save"));
		
		//Avant, auto généré
		//ui.click(new JListLocator("toto, 3"));
		
		//Après
		ui.click(new JListLocator("toto,"));
		
		ui.assertThat(new JButtonLocator("Delete").isEnabled());
		ui.click(new JButtonLocator("Delete"));
	}
	
	public void testTheSaveButtonShouldNotBeEnabledRightAfterClickingTheNewButton() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.assertThat(new JButtonLocator("Save").isEnabled(false));
	}
	
	public void testTheButtonForDeleteShouldNotBeEnabledWhereThereIsntASelectedItemInTheList() throws Exception {
				
		IUIContext ui = getUI();
		ui.assertThat(new JButtonLocator("Delete").isEnabled(false));


		

	}
	
	public void testTheButtonForEditShouldNotBeEnabledWhereThereIsntASelectedItemInTheList() throws Exception {
		// A cet endroit caucune adresse n'est stockée.
		
		IUIContext ui = getUI();
		ui.assertThat(new JButtonLocator("Edit").isEnabled(false));
		
	
	}
	
	public void testTheButtonForEditShouldBeEnabledWhereThereIsASelectedItemInTheList() throws Exception {
		// A cet endroit caucune adresse n'est stockée.
		
		IUIContext ui = getUI();

		// On ajoute une personne. 
		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText("totoName");
		ui.click(new LabeledTextLocator("First Name"));
		ui.enterText("totoFName");

		ui.click(new JButtonLocator("Save"));
		ui.click(new JListLocator("totoName, totoFName"));		
		
		ui.assertThat(new JButtonLocator("Edit").isEnabled());
	
		// On efface l'entrée que l'on vient de créée.
		ui.click(new JButtonLocator("Delete"));

	}
	
	public void testTheButtonForCancelShouldNotBeEnabledWhenWeAreNotEditingAnAddress() throws Exception {
		// A cet endroit caucune adresse n'est stockée.
		
		IUIContext ui = getUI();
		ui.assertThat(new JButtonLocator("Cancel").isEnabled(false));

		ui.click(new JButtonLocator("New"));	
		ui.click(new JButtonLocator("Cancel"));
		
		ui.assertThat(new JButtonLocator("Cancel").isEnabled(false));
		

	}
	
	public void testTheButtonForCancelShouldNotBeEnabledWhenWeAreEditingAnAddress() throws Exception {
	
		IUIContext ui = getUI();

		ui.click(new JButtonLocator("New"));
		
		
		ui.assertThat(new JButtonLocator("Cancel").isEnabled(true));


	}
	
	

}