package ru.netology.rest.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.rest.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardPage {

    public CardPage(){
        transferTitle.shouldBe(visible);
    }

    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferTitle = $(byText("Пополнение карты"));


    public DashboardPage transferOperation(int amountToTransfer, DataHelper.MyCard myCard){
        amountInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        amountInput.setValue(String.valueOf(amountToTransfer));
        //$("[data-test-id='from'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromInput.setValue(myCard.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }
}
