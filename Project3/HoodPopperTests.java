import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HoodPopperTests {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the start page for the decompiler before each test
	@Before
	public void setUp() throws Exception{
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	/**
	 * As a user,
	 * I would like to tokenize ruby code
	 * So that I can understand how ruby tokenizers work.
	 * @author Nathan Anuskiewicz
	 */
	
	
	//Given that I am on the main page
	//Then I see that there is a button to Tokenize Input
	
	@Test
	public void testTokenizeExist() {
		
		//Check that the Tokenize button exists
		
		//If the element is not found, an exception will be thrown and the test will fail.
		WebElement button = driver.findElement(By.name("commit"));
		
		
	}
	
	//Given that I am on the main page
	//When I enter a = 5 into the text box and click tokenize
	//Then I see each space tokenized as "on_sp"
	
	@Test
	public void testTokenizeSpaces() {
		
		//Enter 'a = 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 5");
		
		//Click the Tokenize button
		
		WebElement button = driver.findElement(By.name("commit"));
		button.click();
		
		//assert that the second and third characters are tokenized as spaces
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("[[1, 1], :on_sp, \" \"]"));
		assertTrue(result.contains("[[1, 3], :on_sp, \" \"]"));
	}
	
	
	
	//Given that I am on the main page
	//When I enter a = 5 into the text box and click tokenize
	//Then I see 'a' tokenized as an ":on_ident" type 
	
	@Test
	public void testTokenizeVars(){
		
		//Enter 'a = 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 5");
				
		//Click the Tokenize button
			
		WebElement button = driver.findElement(By.name("commit"));
		button.click();
				
		//assert that the variable 'a' is tokenized as an identifier
			
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("[[1, 0], :on_ident, \"a\"]"));
	}
	
	//Given that I am on the main page
	//When I enter 'puts "Hello"' into the text box and click tokenize
	//Then I see 'puts' tokenized as an ":on_ident" type
	
	@Test
	public void testTokenizeMethod(){
		
		//Enter 'puts "Hello"' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("puts \"Hello\"");
			
		//Click the Tokenize button
				
		WebElement button = driver.findElement(By.name("commit"));
		button.click();
		
		//assert that the puts command is tokenized as an identifier
				
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("[[1, 0], :on_ident, \"puts\"]"));
	}
	
	//Given that I am on the main page
	//When I enter two lines of code and click tokenize
	//Then I see one ":on_nl" type displayed
	
	@Test
	public void testTokenizeNewLine(){
		
		//Enter 'puts "Hello"' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("puts \"Hello\"" + "\n" + "a = 5");
		
		//Click the Tokenize button
				
		WebElement button = driver.findElement(By.name("commit"));
		button.click();
			
		//assert that the last character on the first line is tokenized as a newline character
				
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("[[1, 12], :on_nl, \"\\r\\n\"]"));
	}

	//Given that I am on the main page
	//When I enter "a = 1 + 5" in the text box and click Tokenize
	//Then I see '+' tokenized as an ":on_op" type

	@Test
	public void testTokenizeOperators(){
		
		//Enter 'a = 1 + 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 1 + 5"); 
			
		//Click the Tokenize button
			
		WebElement button = driver.findElement(By.name("commit"));
		button.click();
				
		//assert that the '+' symbol is tokenized as an operator
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("[[1, 6], :on_op, \"+\"]"));
	}
	
	//Mark in summary. Does not go back.
//	@Test
//	public void testTokenizeBack(){
//		
//		//Move to tokenize output page
//		WebElement button = driver.findElement(By.name("commit"));
//		button.click();
//	
//		WebElement link = driver.findElement(By.linkText("Back"));
//		link.click();
//		try{
//		Thread.sleep(10000);
//		}catch(Exception e){}
//		
//		System.out.println(link.getAttribute("onclick"));
//		
//		
//		System.out.println(driver.getPageSource());
//		
//		
//		assertEquals("http://lit-bayou-7912.herokuapp.com/",driver.getCurrentUrl());
//	}
	
	
	/**
	 * As a user,
	 * I would like to parse ruby code
	 * So that I can understand how ruby parsers work.
	 * @author Nathan Anuskiewicz
	 */
	
	//Given that I am on the main page
	//Then I see that there is a button to Parse Input
	
	@Test
	public void testParseExist(){
		
		//Check that the Parse button Exists
		//Parse button should be the second button with the name of commit
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		
		//If element is not found, exception will be thrown and test fails.
		WebElement parse = button.get(1);
		
	}
	
	
	
	//Given that I am on the main page
	//When I enter "a = 1 + 5" into the text area and click Parse
	//Then I see the AST contains no whitespace characters
	

	@Test
	public void testParseWhitespace(){
		
		//Enter 'a = 1 + 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 1 + 5"); 
			
		//Click the Parse button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(1).click();
				
		//store each <p> tag's contents in a list		
		List<WebElement> texts; 
		texts = driver.findElements(By.tagName("p"));
		
		//store the AST into the result variable
		String result = texts.get(1).getText();
		
		//assert that the AST contains no whitespace
		assertFalse(result.contains(" "));
	}
	
	//Given that I am on the main page
	//When I enter a = 1 + 5 into the text area and click Parse
	//Then I see the '+' symbol in the AST
	
	@Test
	public void testParseOperands(){
		
		//Enter 'a = 1 + 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 1 + 5"); 
			
		//Click the Parse button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(1).click();
				
		//store each <p> tag's contents in a list		
		List<WebElement> texts; 
		texts = driver.findElements(By.tagName("p"));
		
		//store the AST into the result variable
		String result = texts.get(1).getText();
		
		//assert that the AST contains the '+' operator
		assertTrue(result.contains("+"));
	}
	
	//Given that I am on the main page
	//When I enter 'puts "Hello"' into the text area and click Parse
	//Then I see the 'puts' command in the AST
	
	@Test
	public void testParsePuts(){
		
		//Enter 'a = 1 + 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("puts \"Hello\""); 
			
		//Click the Parse button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(1).click();
				
		//store each <p> tag's contents in a list		
		List<WebElement> texts; 
		texts = driver.findElements(By.tagName("p"));
		
		//store the AST into the result variable
		String result = texts.get(1).getText();
		
		//assert that the AST contains the puts identifier
		assertTrue(result.contains("puts"));
	}
	
	/**
	 * As a user,
	 * I would like to compile ruby code
	 * So that I can understand how ruby compilers work.
	 * @author Nathan Anuskiewicz
	 */
	
	//Given that I am on the main page
	//Then I see that there is a button to Parse Input
	
	@Test
	public void testCompileExist(){
			
		//Click that the Compile button exists
		//Parse button should be the second button with the name of commit
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		
		//If element not found, exception will be thrown and test will fail
		WebElement compile = button.get(2);
	}
	
	//Given that I am on the main page
	//When I enter 'puts "Hello"' in the text area and click Compile
	//Then I see that 'putstring "Hello"' is included in the disassembled instructions
	
	@Test
	public void testCompilePutString(){
		
		//Enter 'a = 1 + 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("puts \"Hello\""); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that the 'putstring' instruction is called for the string "Hello"
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("putstring \"Hello\""));
	}

	//Given that I am on the main page
	//When I enter a = 1 + 2 in the text area and click Compile
	//Then I see that opt_plus is included in the disassembled instructions
	
	@Test
	public void testCompilePlusOp(){
		
		//Enter 'a = 1 + 2' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 1 + 2"); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that 'opt_plus' instruction is included in the compiled output
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("opt_plus"));
	}
	
	//Given that I am on the main page
	//When I enter a = 8 - 4 in the text area and click Compile
	//Then I see that opt_minus is included in the disassembled instructions
	
	@Test
	public void testCompileMinusOp(){
		
		//Enter 'a = 8 - 4' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 8 - 4"); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that 'opt_minus' is included in the compiled output
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("opt_minus"));
	}
	
	//Given that I am on the main page
	//When I enter a = 5 * 3 in the text area and click Compile
	//Then I see that opt_muls is included in the disassembled instructions
	
	@Test
	public void testCompileMultOp(){
		
		//Enter 'a = 5 * 3' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 5 * 3"); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that 'opt_mult' instruction is included in the compiled output
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("opt_mult"));
	}
	
	//Given that I am on the main page
	//When I enter a = 10 / 5 in the text area and click Compile
	//Then I see that opt_div is included in the disassembled instructions
	
	@Test
	public void testCompileDivOp(){
		
		//Enter 'a = 10 / 5' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 10 / 5"); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that 'opt_div' instruction is included in the compiled output
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("opt_div"));
	}
		
	//Given that I am on the main page
	//When I enter a = 1 + 2 in the text area and click Compile
	//Then I see that putobject is included in the disassembled instructions for the number '2'
	
	@Test
	public void testCompilePutobject(){
		
		//Enter 'a = 1 + 2' into the text box
		
		WebElement e = driver.findElement(By.id("code_code"));
		e.sendKeys("a = 1 + 2"); 
			
		//Click the Compile button
		List<WebElement> button;
		button = driver.findElements(By.name("commit"));
		button.get(2).click();
				
		//assert that 2 was pushed onto the stack
		
		String result = driver.findElement(By.tagName("code")).getText();
		assertTrue(result.contains("putobject 2"));
	}
	
		
	
}


	
