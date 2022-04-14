package ru.netology.rest.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Html;
import lombok.val;
import ru.netology.rest.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.impl.Html.text;


public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void Dashboard() {
    }

    public int getCardBalance(DataHelper.MyCard myCard) {
        val text = cards.findBy(Condition.text(myCard.getCardNumber().substring(16, 19))).getText();
        return extractBalance(text);
    }

    public CardPage selectCardToTransfer(DataHelper.MyCard cardInfo) {
        cards.findBy(Condition.text(cardInfo.getCardNumber().substring(16, 19))).$("button").click();
        return new CardPage();
    }



    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
