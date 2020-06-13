package es.codeurjc.ais.tictactoe;



import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;





import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

class SystemTest {

	private WebDriver browser1;
	private WebDriver browser2;
	private String nombreJ1;
	private String nombreJ2;
	
	
	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebApp.start();
		
	}
	
	@AfterAll
	public static void teardownClass() {
		WebApp.stop();
	}
	
	@BeforeEach
	public void setupTest() {
		browser1 = new ChromeDriver();
		browser2 = new FirefoxDriver();
		browser1.get("http://localhost:8080/");
		browser2.get("http://localhost:8080/");				
		nombreJ1="Alex";
		nombreJ2="Joel";
		WebElement nickJ1=browser1.findElement(By.id("nickname"));
		WebElement nickJ2=browser2.findElement(By.id("nickname"));
		WebElement playJ1=browser1.findElement(By.id("startBtn"));
		WebElement playJ2=browser2.findElement(By.id("startBtn"));
		nickJ1.sendKeys(nombreJ1);
		nickJ2.sendKeys(nombreJ2);
		playJ1.click();
		playJ2.click();
		
	}
	
	@AfterEach
	public void teardown() {		
		if (browser1 != null) {
			browser1.quit();
		}
		if (browser2 != null) {
			browser2.quit();
		}
	}
	
	
	
	
	@DisplayName("Primer jugador en poner ficha gana")
	@Test
	void primeroEnJugarGana() {	
		WebDriverWait waitBoard1 = new WebDriverWait(browser1, 20);
		waitBoard1.until(ExpectedConditions.elementToBeClickable(By.id("cell-1")));
		WebDriverWait waitBoard2 = new WebDriverWait(browser2, 20);
		waitBoard2.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		
		WebElement cell1=browser1.findElement(By.id("cell-1"));
		cell1.click();
		WebElement cell0=browser2.findElement(By.id("cell-0"));
		cell0.click();
		WebElement cell4=browser1.findElement(By.id("cell-4"));
		cell4.click();
		WebElement cell3=browser2.findElement(By.id("cell-3"));
		cell3.click();
		WebElement cell7=browser1.findElement(By.id("cell-7"));		
		cell7.click();
		
		WebDriverWait waitAlert1 = new WebDriverWait(browser1, 30);
		waitAlert1.until(ExpectedConditions.alertIsPresent());
		WebDriverWait waitAlert2 = new WebDriverWait(browser2, 30);
		waitAlert2.until(ExpectedConditions.alertIsPresent());
		    		  		
		String alert1=browser1.switchTo().alert().getText();
		String alert2=browser2.switchTo().alert().getText();
		String alertContent=nombreJ1 + " wins! " + nombreJ2 + " looses.";
			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}
	
	@DisplayName("Primer jugador en poner ficha pierde")
	@Test
	void primeroEnJugarPierde() {
		WebDriverWait waitBoard1 = new WebDriverWait(browser1, 20);
		waitBoard1.until(ExpectedConditions.elementToBeClickable(By.id("cell-2")));
		WebDriverWait waitBoard2 = new WebDriverWait(browser2, 20);
		waitBoard2.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		
		WebElement cell2=browser1.findElement(By.id("cell-2"));
		cell2.click();
		WebElement cell0=browser2.findElement(By.id("cell-0"));
		cell0.click();
		WebElement cell5=browser1.findElement(By.id("cell-5"));
		cell5.click();
		WebElement cell4=browser2.findElement(By.id("cell-4"));
		cell4.click();
		WebElement cell3=browser1.findElement(By.id("cell-3"));		
		cell3.click();
		WebElement cell8=browser2.findElement(By.id("cell-8"));
		cell8.click();
		
		WebDriverWait waitAlert1 = new WebDriverWait(browser1, 30);
		waitAlert1.until(ExpectedConditions.alertIsPresent());
		WebDriverWait waitAlert2 = new WebDriverWait(browser2, 30);
		waitAlert2.until(ExpectedConditions.alertIsPresent());
		    		  		
		String alert1=browser1.switchTo().alert().getText();
		String alert2=browser2.switchTo().alert().getText();
		String alertContent=nombreJ2 + " wins! " + nombreJ1 + " looses.";
			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}
	
	@DisplayName("Los jugadores empatan")
	@Test
	void empate() {
		WebDriverWait waitBoard1 = new WebDriverWait(browser1, 20);
		waitBoard1.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		WebDriverWait waitBoard2 = new WebDriverWait(browser2, 20);
		waitBoard2.until(ExpectedConditions.elementToBeClickable(By.id("cell-1")));
		
		WebElement cell0=browser1.findElement(By.id("cell-0"));
		cell0.click();
		WebElement cell1=browser2.findElement(By.id("cell-1"));
		cell1.click();
		WebElement cell4=browser1.findElement(By.id("cell-4"));
		cell4.click();
		WebElement cell8=browser2.findElement(By.id("cell-8"));
		cell8.click();
		WebElement cell5=browser1.findElement(By.id("cell-5"));		
		cell5.click();
		WebElement cell6=browser2.findElement(By.id("cell-6"));
		cell6.click();
		WebElement cell7=browser1.findElement(By.id("cell-7"));		
		cell7.click();
		WebElement cell3=browser2.findElement(By.id("cell-3"));
		cell3.click();
		WebElement cell2=browser1.findElement(By.id("cell-2"));		
		cell2.click();
		
		WebDriverWait waitAlert1 = new WebDriverWait(browser1, 30);
		waitAlert1.until(ExpectedConditions.alertIsPresent());
		WebDriverWait waitAlert2 = new WebDriverWait(browser2, 30);
		waitAlert2.until(ExpectedConditions.alertIsPresent());
		    		  		
		String alert1=browser1.switchTo().alert().getText();
		String alert2=browser2.switchTo().alert().getText();
		String alertContent="Draw!";
			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}

}

