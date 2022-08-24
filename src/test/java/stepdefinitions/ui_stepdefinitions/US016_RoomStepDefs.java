package stepdefinitions.ui_stepdefinitions;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import pages.admin_pages.Admin_RoomsPage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.junit.Assert.assertEquals;
import static utilities.Driver.waitForVisibility;
import static utilities.ReusableMethods.waitFor;


public class US016_RoomStepDefs {

    Admin_RoomsPage page=new Admin_RoomsPage();


    String actualID;
    String deleteID;
    String sucsessMessage;

    @And("admin Rooms sayfasina gecis yapar")
    public void adminRoomsSayfasinaGecisYapar() {
        page.itemTitlesMenu.click();
        page.roomButton.click();
    }

    @Then("url nin {string} icerdigini dogrular")
    public void urlNinIcerdiginiDogrular(String testData) {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(testData));
    }

    @Then("basligin {string} icerdigini dogrular")
    public void basliginIcerdiginiDogrular(String testData) {
        System.out.println(page.pageHead.getText());
        Assert.assertTrue(page.pageHead.getText().contains(testData));
    }

    @And("tablo da {string} {string} {string} {string} {string} {string} {string} basliklarinin gorunur oldugunu dogrular")
    public void tabloDaBasliklarininGorunurOldugunuDogrular(String ID, String RoomNumber, String RoomType,
                                                            String Status, String Price, String Description, String CreatedDate) {
        Assert.assertTrue(page.headers.getText().contains(ID));
        Assert.assertTrue(page.headers.getText().contains(RoomNumber));
        Assert.assertTrue(page.headers.getText().contains(RoomType));
        Assert.assertTrue(page.headers.getText().contains(Status));
        Assert.assertTrue(page.headers.getText().contains(Price));
        Assert.assertTrue(page.headers.getText().contains(Description));
        Assert.assertTrue(page.headers.getText().contains(CreatedDate));
    }

    @And("Create a new Room butonunun erisilebilir ve gorunur oldugunu dogrular")
    public void createANewRoomButonununErisilebilirVeGorunurOldugunuDogrular() {
        Assert.assertTrue(page.createButton.isDisplayed());
        Assert.assertTrue(page.createButton.isEnabled());
    }

    @And("admin Room Type gore odalari siralar")
    public void adminRoomTypeGoreOdalariSiralar() {
        page.RoomType.click();
        waitFor(3);
    }

    @Then("admin TWIN odalarin siralandigini dogrular")
    public void adminTWINOdalarinSiralandiginiDogrular() {
        for (int i = 1; i <=20 ; i++) {
            assertEquals(Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[3]//span")).getText(),"TWIN");
        }
    }

    @And("admin SUITE odalarin sirlandigini dogrular")
    public void adminSUITEOdalarinSirlandiginiDogrular() {
        Driver.getDriver().get("https://medunna.com/room?page=51&sort=id,asc");
        for (int i = 1; i <=20 ; i++) {
            assertEquals(Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[3]//span")).getText(),"SUITE");
        }
    }

    @And("admin PREMIUM_DELUXE odalarin siralndigini dogrular")
    public void adminPREMIUM_DELUXEOdalarinSiralndiginiDogrular() {
        Driver.getDriver().get("https://medunna.com/room?page=58&sort=id,asc");
        for (int i = 1; i <=20 ; i++) {
            assertEquals(Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[3]//span")).getText(),"PREMIUM_DELUXE");
        }
    }

    @And("admin DELUXE odalarin siralandigini dogrular")
    public void adminDELUXEOdalarinSiralandiginiDogrular() {
        Driver.getDriver().get("https://medunna.com/room?page=62&sort=id,asc");
        for (int i = 1; i <=20 ; i++) {
            assertEquals(Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[3]//span")).getText(),"DELUXE");
        }
    }

    @And("admin DAYCARE odalarin siralandigini dogrular")
    public void adminDAYCAREOdalarinSiralandiginiDogrular() {
        Driver.getDriver().get("https://medunna.com/room?page=80&sort=id,asc");
        for (int i = 1; i <=20 ; i++) {
            assertEquals(Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[3]//span")).getText(),"DAYCARE");
        }
    }

    @And("Create a new Room butonuna tiklar")
    public void createANewRoomButonunaTiklar() {
        page.createButton.click();
    }

    @And("Room Number {string} girer")
    public void roomNumberGirer(String roomNumber) {
        page.roomNumberTextbox.clear();
        page.roomNumberTextbox.sendKeys(roomNumber);
    }

    @And("Room Type {string} secer")
    public void roomTypeSecer(String roomType) {
        Select select=new Select(page.roomTypeddm);
        select.selectByVisibleText(roomType);
    }

    @And("Price {string} girer")
    public void priceGirer(String price) {
        page.roomPriceTextbox.clear();
        page.roomPriceTextbox.sendKeys(price);
    }

    @And("Description {string} girer")
    public void descriptionGirer(String desc) {
        page.roomDescriptionTextbox.clear();
        page.roomDescriptionTextbox.sendKeys(desc);
    }

    @And("Created Date {string} girer")
    public void createdDateGirer(String date) {
        page.roomDateTextbox.sendKeys(date);
    }

    @And("Save butonuna tiklar")
    public void saveButonunaTiklar() {
        page.saveButton.click();
        waitFor(2);
    }

    @Then("{string} yazisinin gorunur oldugunu dogrular")
    public void yazisininGorunurOldugunuDogrular(String sucsess) {
        Assert.assertTrue(page.sucsessMesage.isDisplayed());
        Assert.assertTrue(page.sucsessMesage.getText().contains(sucsess));
        sucsessMessage=page.sucsessMesage.getText();
        System.out.println(sucsessMessage);
        actualID=sucsessMessage.substring(38);
        System.out.println(actualID);
    }

    @Then("Create or Edit sayfasinin acildigini dogrular")
    public void createOrEditSayfasininAcildiginiDogrular() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("room/new"));
        assertEquals(page.createPageHead.getText(),"Create or edit a Room");
    }


    @And("yeni olusturulan odanin sayfasina navigate eder")
    public void yeniOlusturulanOdaninSayfasinaNavigateEder() {
        page.ID.click();
    }

    @Then("yeni odanin id sinin gorunur oldugunu dogrular")
    public void yeniOdaninIdSininGorunurOldugunuDogrular() {
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+actualID+"']")).isDisplayed());
    }

    @And("view butonuna tiklar")
    public void viewButonunaTiklar() {
        Driver.getDriver().findElement(By.xpath("(//a[@href='/room/"+actualID+"'])[2]")).click();
    }

    @Then("yeni odanin bilgilerinin {string} {string} {string} {string} oldugunu dogrular")
    public void yeniOdaninBilgilerininOldugunuDogrular(String roomNumber, String roomType, String price, String desc) {
        Assert.assertTrue(page.viewTable.getText().contains(roomNumber));
        Assert.assertTrue(page.viewTable.getText().contains(roomType));
        Assert.assertTrue(page.viewTable.getText().contains(price));
        Assert.assertTrue(page.viewTable.getText().contains(desc));
    }

    @Then("created date {string} oldugunu dogrular")
    public void createdDateOldugunuDogrular(String date) {
        Assert.assertTrue("EXPECTED DATE ---> "+date+" \nACTUAL DATE ---> "+page.dateActual.getText()+" ",
                page.viewTable.getText().contains(date));
    }

    @And("{string} id li odanin Edit butonuna tiklar")
    public void idLiOdaninEditButonunaTiklar(String id) {
        Driver.getDriver().findElement(By.xpath("//a[@href='/room/"+id+"/edit?page=1&sort=id,asc']")).click();
    }

    @Then("{string} yazisinin oldugunu dogrular")
    public void yazisininOldugunuDogrular(String sucsess) {
        Assert.assertTrue(waitForVisibility(page.sucsessMesage,2).isDisplayed());
        Assert.assertTrue(page.sucsessMesage.getText().contains(sucsess));
    }

    @And("{string} id li odanin View butonuna tiklar")
    public void idLiOdaninViewButonunaTiklar(String id) {
        Driver.getDriver().findElement(By.xpath("(//a[@href='/room/"+id+"'])[2]")).click();
    }

    @And("olusturdugu odaya gecis yapar")
    public void olusturduguOdayaGecisYapar() {
        page.createButton.click();
        page.roomNumberTextbox.sendKeys("5930");
        page.roomPriceTextbox.sendKeys("30");
        page.saveButton.click();
        waitFor(2);
        deleteID=page.sucsessMesage.getText().substring(38);
        page.ID.click();
    }

    @And("Delete butonuna tiklar")
    public void deleteButonunaTiklar() {
        Driver.getDriver().findElement(By.xpath("//a[@href='/room/"+deleteID+"/delete?page=1&sort=id,desc']")).click();
        waitFor(4);
    }

    @And("dogrulama sayfasinda Delete butonuna tiklar")
    public void dogrulamaSayfasindaDeleteButonunaTiklar() {
        page.deleteButton.click();
    }

    @Then("silinen odanin id sinin olmadigini dogrular")
    public void silinenOdaninIdSininOlmadiginiDogrular() {
        for (int i = 1; i <20 ; i++) {
            Assert.assertNotEquals(deleteID,Driver.getDriver().findElement(By.xpath("//tr["+i+"]//td[1]")).getText());}
    }
}