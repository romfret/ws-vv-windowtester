import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.locator.JButtonLocator;

public class hh extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public hh() {
		super(com.sun.demo.addressbook.AddressFrame.class);
	}

	/**
	 * Main test method.
	 */
	public void testhh() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JListLocator("CADAVID, Juan Jose: 103"));
		ui.click(new JButtonLocator("Delete"));
	}

}