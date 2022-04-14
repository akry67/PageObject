package ru.netology.rest.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.rest.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
  private SelenideElement codeField = $("[data-test-id=code] input");
  private SelenideElement verifyButton = $("[data-test-id=action-verify]");

  private SelenideElement firstCardTransaction = $("[data-test-id=action-deposit]");
  private SelenideElement secondCardTransaction =$("[data-test-id=action-deposit]");

  public VerificationPage() {
    codeField.shouldBe(visible);
  }

  public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
    codeField.setValue(verificationCode.getCode());
    verifyButton.click();
    return new DashboardPage();
  }
}
