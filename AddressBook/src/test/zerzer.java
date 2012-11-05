package test;

import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;
import com.windowtester.runtime.swing.locator.JListLocator;

public class zerzer extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public zerzer() {
		super(com.sun.demo.addressbook.AddressFrame.class);
	}

	/**
	 * Main test method.
	 */
	public void testzerzer() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText("toto");
		ui.click(new JButtonLocator("Save"));
		ui.click(new JListLocator("toto,  : 5"));
		ui.assertThat(new JButtonLocator("Delete").isEnabled());
	}

}