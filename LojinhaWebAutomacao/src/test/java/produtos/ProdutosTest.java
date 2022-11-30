package produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@DisplayName("Teste Web do Módulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor menor a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorMenorAZero(){

        // ABRIR O NAVEGADOR;
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //MAXIMIZAR A TELA
        navegador.manage().window().maximize();


        //VOU DEFINIR UM TEMPO DE ESPERA DE 5 SEGUNDOS
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //NAVEGAR PARA A PÁGINA DA LOJINHA WEB;
        navegador.get("http://165.227.93.41/lojinha-web/v2/?error=Acesse%20a%20lojinha");

        //FAZER LOGIN;
        navegador.findElement(By.cssSelector("label[for=\"usuario\"]")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for=\"senha\"]")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        //IR PARA A TELA DE REGISTRO DE PRODUTOS; //17:13
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //PREENCHER DADOS DO PRODUTOI E O VALOR VAI SER IGUAL A ZERO;
        navegador.findElement(By.id("produtonome")).sendKeys("Batata frita");
        navegador.findElement(By.id("produtovalor")).sendKeys("0.00");
        navegador.findElement(By.id("produtocores")).sendKeys("amarela");
        //SUBMETER O FORMULÁRIO;
        navegador.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        //VALIDAR QUE A MENSAGEM DE ERRO FOI EXECUTADA;
        //<div class="toast rounded" style="top: 0px; opacity: 1;">O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00</div>
        String mensagemToast =navegador .findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        //FECHAR O NAVEGADOR;
        navegador.close();
    }

}
