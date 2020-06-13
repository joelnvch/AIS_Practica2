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

	private WebDriver browser1,browser2;
	private String nombreJ1,nombreJ2;
	private WebDriverWait waitBoard1,waitBoard2,waitAlert1,waitAlert2;
	private String alert1,alert2;
	
	
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
		waitBoard1 = new WebDriverWait(browser1, 20);
		waitBoard2 = new WebDriverWait(browser2, 20);		
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
	void primeroEnJugarGanaTest() {
		int[] movimientos= {1, 0, 4, 3, 7};
		jugar(movimientos);		
		esperarCuadrosDeDialogo();		    		  		
		guardarCuadrosDeDialogo();
		
		String alertContent=nombreJ1 + " wins! " + nombreJ2 + " looses.";			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}

		
	@DisplayName("Primer jugador en poner ficha pierde")
	@Test
	void primeroEnJugarPierdeTest() {	
		int[] movimientos = {2, 0, 5, 4, 3, 8};
		jugar(movimientos);				
		esperarCuadrosDeDialogo();		    		  		
		guardarCuadrosDeDialogo();
		
		String alertContent=nombreJ2 + " wins! " + nombreJ1 + " looses.";			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}
	
	
	@DisplayName("Los jugadores empatan")
	@Test
	void empateTest() {
		int[] movimientos = {0, 1, 4, 8, 5, 6, 7, 3, 2};
		jugar(movimientos);
		esperarCuadrosDeDialogo();		    		  		
		guardarCuadrosDeDialogo();
		
		String alertContent="Draw!";			
		assertThat(alert1,equalTo(alertContent));
		assertThat(alert2,equalTo(alertContent));												
	}
	
	
	private void jugar(int [] mov) {
		waitBoard1.until(ExpectedConditions.presenceOfElementLocated(By.id("cell-" + mov[0])));		
		waitBoard2.until(ExpectedConditions.presenceOfElementLocated(By.id("cell-" + mov[1])));
		int cont=0;
		for(int i:mov) {
			if (cont%2==0) 
				browser1.findElement(By.id("cell-" + i)).click();
			else
				browser2.findElement(By.id("cell-" + i)).click();
			cont++;
		}
	}
	
	private void esperarCuadrosDeDialogo() {
		waitAlert1 = new WebDriverWait(browser1, 60);
		waitAlert1.until(ExpectedConditions.alertIsPresent());
		waitAlert2 = new WebDriverWait(browser2, 60);
		waitAlert2.until(ExpectedConditions.alertIsPresent());
	}
	
	private void guardarCuadrosDeDialogo() {
		alert1=browser1.switchTo().alert().getText();
		alert2=browser2.switchTo().alert().getText();
	}

}

