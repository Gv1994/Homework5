# Homework5
package AutoM;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationPage {



    @Test
    public void regPage() {

        openRegPage(); // რეგისტრაციის გვერდი

        // რეგისტრაციის გვერდი 
        Assert.assertEquals("სწრაფი რეგისტრაცია", $(".registration-titles").getText());


        // სახელი

        $("#firstName").click();
        $("#firstName").setValue(name);
        Assert.assertEquals(name, $("#firstName").getValue());
        //Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#firstName").is(Condition.empty));


        // გვარის 

        $("#lastName").click();
        $("#lastName").setValue(fakelastName);
        Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#lastName").is(Condition.empty));

        $("#email").click(); // იმეილის  შევსება, გადამომწება
        $("#email").setValue(email);
        Assert.assertFalse($("#email").is(Condition.empty));


        // პაროლი

        $("#password").click();
        $("#password").setValue(pass);
        Assert.assertEquals(pass, $("#password").getValue());

        // პაროლის განმეორება

        $("#confirmPassword").click();
        $("#confirmPassword").setValue(pass);
        Assert.assertEquals(pass, $("#confirmPassword").getValue());


        // enabled ღილაკი

        Assert.assertTrue( $("#singup").is(Condition.enabled));
        //Assert.assertEquals("რეგისტრაცია", $("#singup").getText());

        sleep(5000);

    }



        //Negative Test case

    @Test
    public void regPageNegative(){

        openRegPage(); // რეგისტრაციის გვერდი

        // რეგისტრაციის გვერდი - გადამოწმება
        Assert.assertEquals("სწრაფი რეგისტრაცია", $(".registration-titles").getText());

        //რეგისტრაცია ღილაკი არის Disabled

        Assert.assertTrue( $("#singup").is(Condition.disabled));

        //

        $("#firstName").click();
        $("#lastName").click();
        Assert.assertEquals("სახელი სავალდებულოა", $(".text-danger").getText());


        //გვარი

        $("#lastName").click();
        $("#email").click();
        //$(byText("გვარი სავალდებულოა")).shouldHave(Condition.visible);
        Assert.assertEquals("გვარი სავალდებულოა", $(".text-danger",1).getText());

        //იმეილი

        $("#email").click();
        $("#password").click();
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());

        //პაროლის

        $("#password").click();
        $("#confirmPassword").click();
        Assert.assertEquals("პაროლი სავალდებულოა.", $(".text-danger",3).getText());

        //განმეორებითი პაროლი

        $("#confirmPassword").click();
        $("#password").click();
        Assert.assertEquals("პაროლის გამეორება სავალდებულოა", $(".text-danger",4).getText());


        //Email არასწორი ფორმატი
        // 1
        $("#email").setValue("test");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        sleep(2000);

        // 2
        $("#email").setValue("test@");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);

        // 3
        $("#email").setValue("test@gmail");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);

        // 4
        $("#email").setValue("test@gmail.");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        

        // პაროლის ველი 6 სიმბოლოზე ნაკლები

        $("#password").click();
        $("#password").setValue("6746");
        //Assert.assertEquals("პაროლი სავალდებულოა.", $(".text-danger",2).getText());
        Assert.assertTrue( $(byText("პაროლი სავალდებულოა.")).isDisplayed());


        // 

        $("#password").setValue("647382635");

        $("#confirmPassword").setValue("7584632537364");
        Assert.assertTrue( $(byText("პაროლის გამეორება სავალდებულოა")).isDisplayed());
        sleep(5000);

       /*if ($("#password").getValue() == $("#confirmPassword").getValue()) {
       }*/


   


}
