package ru.netology.rest.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.rest.data.DataHelper;
import ru.netology.rest.page.CardPage;
import ru.netology.rest.page.DashboardPage;
import ru.netology.rest.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.rest.data.DataHelper.getFirstCard;
import static ru.netology.rest.data.DataHelper.getSecondCard;

class MoneyTransferTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTransferFromFirstToSecond() throws InterruptedException {
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCard();
        var secondCardInfo = getSecondCard();
        int amount = 10000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.transferOperation(parseInt(String.valueOf(amount)), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        var backAmountSecondCard = dashboardPage.getCardBalance(secondCardInfo) - amount;
        var backAmountFirstCard = dashboardPage.getCardBalance(firstCardInfo) + amount;
        var transferPageBack = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPageBack.transferOperation(parseInt(String.valueOf(amount)), secondCardInfo);
        var actualBackBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBackBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
    }

    @Test
    void shouldTransferFromSecondToFirst() {
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCard();
        var secondCardInfo = getSecondCard();
        int amount = 1000;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) - amount;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.transferOperation(parseInt(String.valueOf(amount)), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        var backAmountSecondCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var backAmountFirstCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPageBack = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPageBack.transferOperation(parseInt(String.valueOf(amount)), firstCardInfo);
        var actualBackBalanceFirstCard = dashboardPage.getCardBalance(secondCardInfo);
        var actualBackBalanceSecondCard = dashboardPage.getCardBalance(firstCardInfo);
    }
    
}

