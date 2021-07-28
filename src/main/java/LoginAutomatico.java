import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class LoginAutomatico {

    static WebDriver chrome;



    public static void main(String[] args) {

        chrome = new ChromeDriver();
        chrome.get("https://login.microsoftonline.com/common/oauth2/authorize?response_type=id_token&client_id=5e3ce6c0-2b1f-4285-8d4b-75ee78787346&redirect_uri=https%3A%2F%2Fteams.microsoft.com%2Fgo&state=50c43175-8c52-4091-b196-dfeb6bce3dbc&client-request-id=d3513d3a-7ce2-40f5-b1a4-2adca3ab1841&x-client-SKU=Js&x-client-Ver=1.0.9&nonce=94685b8b-8668-40b8-90f8-1a981cddd435&domain_hint=&sso_reload=true");

        WebElement loginbusca = fluentWait(By.id("i0116"));
        loginbusca.sendKeys("usuario|email");
        fluentWait(By.id("idSIButton9")).click();

        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        fluentWait(By.name("Password")).sendKeys("senhaLogin");
        fluentWait(By.id("submitButton")).click();

        WebElement permanenciaconexao = fluentWait(By.id("idSIButton9"));
        permanenciaconexao.click();


        WebElement calendario = fluentWait(By.id("app-bar-ef56c0de-36fc-4ef8-b417-3d82ba9d073c"));
        calendario.click();

        fluentWait(By.xpath("//span[@class='ms-Button-flexContainer flexContainer-41']//span[@id='id__16']")).click();
        fluentWait(By.xpath("//span[contains(text(),'Dia')]")).click();

        List <WebElement> listadiv = fluentList(By.xpath("//div[@tabindex='-1']"));
        listadiv.forEach(webElement ->  System.out.println(webElement.getAttribute("aria-label")));

        String [][] matriz = new String[24][11];

        for (int i = 0; i<14; i++){
            listadiv.remove(i);
        }

        for (int i = 0; i<14; i++){
            matriz[i] = listadiv.get(i).getAttribute("aria-label").split(" ");
        }

        listadiv.forEach(System.out::println);




    }

    private static WebElement fluentWait(final By localizador){
        Wait<WebDriver> wait = new FluentWait<>(chrome)
                .withTimeout(Duration.ofSeconds(60))//Limite de tempo que uma busca de um elemento deve ser realizada
                .pollingEvery(Duration.ofSeconds(1))//com que frequencia um elemento deve ser buscado na tela
                .ignoring(NoSuchElementException.class);
        return wait.until((webdriver) -> chrome.findElement(localizador));
        }
        private static List<WebElement> fluentList(final By localizador){
            Wait<WebDriver> wait = new FluentWait<>(chrome)
                .withTimeout(Duration.ofSeconds(60))//Limite de tempo que uma busca de um elemento deve ser realizada
                .pollingEvery(Duration.ofSeconds(1))//com que frequencia um elemento deve ser buscado na tela
                .ignoring(NoSuchElementException.class);
            return wait.until((webdriver) -> chrome.findElements(localizador));
    }
}
