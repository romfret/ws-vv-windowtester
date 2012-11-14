package test;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import com.sun.demo.addressbook.db.AddressDao;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.WT;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.swing.condition.WindowDisposedCondition;
import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.locator.JListLocator;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;

/**
 * To the generated class by WindowTesterPro, you have to override setUp() to
 * establish the connection to the DB in order to test it.
 * 
 * @author Juan
 * 
 */
public class DBTests extends UITestCaseSwing {

	private final String dbName = "DefaultAddressBook";
	private FlatXmlDataSet expected;
	private IDatabaseConnection connection;
	private JdbcDatabaseTester tester;
	private IDataSet actual;

	@Override
	protected void setUp() throws Exception {

		WT.setLocaleToCurrent();


		FileInputStream f = new FileInputStream("data/dataset1address.xml");
		FlatXmlDataSetBuilder fx = new FlatXmlDataSetBuilder();
		FlatXmlDataSet initialData = fx.build(f);
		expected = new FlatXmlDataSetBuilder().build(new FileInputStream(
				"data/dataset2addresses.xml"));
		new AddressDao();
		tester = new JdbcDatabaseTester("org.apache.derby.jdbc.EmbeddedDriver",
				"jdbc:derby:" + dbName, "addressuser", "addressuser", "APP");
		connection = tester.getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(connection, initialData);
		run();
	}

	/**
	 * Create an Instance
	 */
	public DBTests() {
		super(com.sun.demo.addressbook.AddressFrame.class);
	}

	/**
	 * Main test method.
	 */
	public void testAddContactData() throws Exception {
		IUIContext ui = getUI();

		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText("LECRIVAIN");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Benoit");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Alexandre");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("0225688722");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("benoit.lecrivain@hotmail.com");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Chateau d Apigne");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Porte 4");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Apigne");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("Bretagne");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("35650");
		ui.keyClick(KeyEvent.VK_TAB);
		ui.enterText("France");
		ui.click(new JButtonLocator("Save"));

		actual = connection.createDataSet();
		String tableName = "Address";
		ITable expectedTable = expected.getTable(tableName);
		ITable actualTable = actual.getTable(tableName);
		Column[] filter = expected.getTableMetaData(tableName).getColumns();
		System.out.println("length="+filter.length);
		ITable actualFilteredTable = DefaultColumnFilter.includedColumnsTable(
				actualTable, filter);
		Assertion.assertEquals(expectedTable, actualFilteredTable);
		// ui.wait(new WindowDisposedCondition("Address Book Demo"));
	}

	/**
	 * Main test method.
	 */
	public void testAddDuplicatedContactData() throws Exception {
		IUIContext ui = getUI();

	
		
		for (int i = 0; i < 2; i++) {
			ui.click(new JButtonLocator("New"));
			ui.click(new LabeledTextLocator("Last Name"));
			ui.enterText("LEBOUCHER");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("Henry");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("Marc");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("0545252525");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("henryleboucher@hotmail.com");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("Chateau du boucher");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("Porte 666");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("VilleBoucher");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("Bretagne");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("35650");
			ui.keyClick(KeyEvent.VK_TAB);
			ui.enterText("France");
			ui.click(new JButtonLocator("Save"));
		}
		assertEquals(1, connection.getRowCount("Address", "where LASTNAME = 'LEBOUCHER' AND FIRSTNAME = 'Henry' AND MIDDLENAME = 'Marc' AND EMAIL = 'henryleboucher@hotmail.com'"));
		
	}

	public void testAddContactWithEmptyTextFields() throws Exception {
		IUIContext ui = getUI();
		int before = connection.getRowCount("Address");
		
		ui.click(new JButtonLocator("New"));
		ui.click(new LabeledTextLocator("Last Name"));
		ui.enterText(""); //Le bouton save devient activé
		ui.click(new JButtonLocator("Save"));
		
		assertEquals(before, connection.getRowCount("Address"));
		
		
	}
	
	public void testDeleteContact() throws Exception {		
		IUIContext ui = getUI();
		int before = connection.getRowCount("Address");
		ui.click(new JListLocator("CADAVID, Juan Jose"));
		ui.click(new JButtonLocator("Delete"));
		assertEquals(before - 1, connection.getRowCount("Address"));
		assertEquals(0, connection.getRowCount("Address", "where LASTNAME = 'CADAVID' AND FIRSTNAME = 'Juan' and MIDDLENAME = 'Jose'"));
	}


	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		connection = tester.getConnection();
		// DatabaseOperation.DELETE_ALL.execute(connection, actual);
		connection.close();
	}

}