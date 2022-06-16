package lecture10;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Excercise5 {
    @Test
    public void test(){
        Selenide.open("https://ee.ge/registration");
        Configuration.browserSize = "1920x1080";

        $("#firstName").setValue("Gvantsa");
        Assert.assertEquals("Gvantsa",$("#firstName").getValue());
        //Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#firstName").is(Condition.empty));


        $("#lastName").setValue("Shanava");
        Assert.assertEquals("Shanava", $("#lastName").getValue());
        Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#lastName").is(Condition.empty));

        $("#email").setValue("gvantsa.shanava@gmail.com");
        Assert.assertFalse($("#email").is(Condition.empty));


        $("#password").setValue("Gvantsa123");
        Assert.assertEquals("Gvantsa123", $("#password").getValue());



        $("#confirmPassword").setValue("Gvantsa123");
        Assert.assertEquals("Gvantsa123", $("#confirmPassword").getValue());

        Assert.assertTrue($("#singup").is(Condition.enabled));
        Assert.assertEquals("რეგისტრაცია", $("#singup").getText());

        sleep(5000);
    }

    @Test
    public void regPageNegative(){
        open("https://ee.ge/registration");
        Assert.assertEquals("სწრაფი რეგისტრაცია",$(".registration-titles").getText());
        Assert.assertTrue($("#singup").is(Condition.disabled));
        $("#firstName").click();
        $("#lastName").click();
        Assert.assertEquals("სახელი სავალდებულოა", $(".text-danger").getText());
        $("#lastName").click();
        $("#email").click();
        $(byText("გვარი სავალდებულოა")).shouldHave(Condition.visible);
        Assert.assertEquals("გვარი სავალდებულოა",$(".text-danger",1).getText());
        $("#email").click();
        $("#password").click();
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#password").click();
        $("#confirmPassword").click();
        Assert.assertEquals("პაროლი სავალდებულოა.", $(".text-danger",3).getText());
        $("#confirmPassword").click();
        $("#password").click();
        Assert.assertEquals("პაროლის გამეორება სავალდებულოა", $(".text-danger",4).getText());

        //1
        $("#email").setValue("test");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        //2
        $("#email").setValue("test@");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        //3
        $("#email").setValue("test@gmail");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        //4
        $("#email").setValue("test@gmail.");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());

        //პაროლის ველი 6>
        $("#password").click();
        $("#password").setValue("1234");
        Assert.assertTrue($(byText("პაროლი სავალდებულოა.")).isDisplayed());

        //პაროლი ველში განსხვავებული მნიშნლობები
        $("#password").setValue("764346");
        $("#confirmPassword").setValue("2742741028");
        Assert.assertTrue($(byText("პაროლის გამეორება სავალდებულოა")).isDisplayed());


        sleep(5000);


    }

}