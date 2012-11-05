package test;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;

public class Test extends
		UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public Test() {
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
		ui.assertThat(new LabeledTextLocator("State").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Country").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("First Name").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Middle Name").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("Email").isEnabled(false));
		ui.assertThat(new LabeledTextLocator("ZIP").isEnabled(false));

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
		ui.click(new JListLocator("toto,"));
		ui.assertThat(new JButtonLocator("Delete").isEnabled());
	}
	
	public void testTheSaveButtonShouldNotBeEnabledRightAfterClickingTheNewButton() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.assertThat(new JButtonLocator("Save").isEnabled(false));
	}
	
	public void testTheButtonForDeleteShouldNotBeEnabledWhereThereIsntASelectedItemInTheList() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText("toto");
		ui.click(new JButtonLocator("Save"));
		ui.click(new JListLocator("toto,"));
		//passer le focus à un autre controle
		//deselectionner l'item
		//asert false
		//selectionner l'item
		//assert true
		ui.assertThat(new JButtonLocator("Delete").isEnabled());
	}
	

}