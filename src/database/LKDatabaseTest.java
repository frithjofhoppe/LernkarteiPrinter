package database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import database.sql.SQLHandler;

/**
 * @author WISS
 *
 */
public class LKDatabaseTest {

	public static void myTest() {
		debug.Debugger.out("Test LKDatabase...");
		String cmd, gets;

		debug.Debugger.out("Test Config...");
		LKDatabase.myConfig.setKeyValue("TestKey", "1234");
		cmd = LKDatabase.myConfig.getLastSQLCommand();
		debug.Debugger.out("SQL: {"+cmd+"}");
// nur das erste mal: assertEquals("INSERT INTO config (KEY_NAME,VALUE) VALUES ('TestKey','1234')",cmd);
// dann update
//		gets = LKDatabase.myConfig.getValue("TestKey");
//		debug.Debugger.out("SQL: {"+gets+"}");
//		assertEquals("1234",gets);
		
		LKDatabase.myDoors.getMyAttributes().seekKeyNamed("name").setValue("hallo!!!");
		cmd = SQLHandler.insertIntoTableCommand(LKDatabase.myDoors.getMyTableName(),LKDatabase.myDoors.getMyAttributes()); 
		//cmd = LKDatabase.myDoors.getLastSQLCommand();
		debug.Debugger.out("SQL: {"+cmd+"}");
		assertEquals("INSERT INTO Door (KEY_NAME, VALUE, Name) VALUES ('','','hallo!!!')",cmd);
	
		cmd = SQLHandler.selectCommand("STACK","PK_STACK","PK_STACK","franz"); 
		debug.Debugger.out("SQL: {"+cmd+"}");
		assertEquals("SELECT PK_STACK FROM STACK WHERE PK_STACK = 'franz' ",cmd);

		LKDatabase.myCards.getMyAttributes().seekKeyNamed("Frontside").setValue("hallo!!!");
		cmd = SQLHandler.insertIntoTableCommand(LKDatabase.myCards.getMyTableName(),LKDatabase.myCards.getMyAttributes()); 
		//cmd = LKDatabase.myDoors.getLastSQLCommand();
		debug.Debugger.out("SQL: {"+cmd+"}");
		assertEquals("INSERT INTO Card (KEY_NAME, VALUE, Frontside, Backside, PK_STACK, Priority, Color, Description, Date) VALUES ('','','hallo!!!','',0,0,'','','')",cmd);
	
	}

	@Test
	public void test() {
		debug.Debugger.out("Start LKDatabase Test...");
		myTest();
	}

}