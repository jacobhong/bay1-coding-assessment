import com.bay1.assessment.domain.AuthorizationRecord;
import com.bay1.assessment.validation.RecordValidator;
import org.junit.Assert;
import org.junit.Test;

import java.time.YearMonth;

public class RecordValidatorTest {

  @Test
  public void testRequiredFieldsPresentValid() {
    var authorizationRecord = new AuthorizationRecord();
    authorizationRecord.setPan("pan");
    authorizationRecord.setTransactionAmount("100");
    authorizationRecord.setExpirationDate(YearMonth.of(11, 11).atDay(1));
    var result = RecordValidator.requiredFieldsPresent(authorizationRecord);
    Assert.assertEquals(true, result);
  }

  @Test
  public void testRequiredFieldsPresentInvalid() {
    var authorizationRecord = new AuthorizationRecord();
    var result = RecordValidator.requiredFieldsPresent(authorizationRecord);
    Assert.assertEquals(false, result);
  }

  @Test
  public void testValidTransAmountWithZip() {
    var authorizationRecord = new AuthorizationRecord();
    authorizationRecord.setTransactionAmount("2000");
    authorizationRecord.setExpirationDate(YearMonth.of(9999, 11).atDay(1));
    var result = RecordValidator.validAuthorization(authorizationRecord);
    Assert.assertEquals(true, result);
  }

  @Test
  public void testInvalidTransAmountWithZip() {
    var authorizationRecord = new AuthorizationRecord();
    authorizationRecord.setTransactionAmount("200000");
    authorizationRecord.setExpirationDate(YearMonth.of(9999, 11).atDay(1));
    var result = RecordValidator.validAuthorization(authorizationRecord);
    Assert.assertEquals(false, result);
  }

  @Test
  public void testValidTransAmountWithoutZip() {
    var authorizationRecord = new AuthorizationRecord();
    authorizationRecord.setTransactionAmount("1000");
    authorizationRecord.setExpirationDate(YearMonth.of(9999, 11).atDay(1));
    var result = RecordValidator.validAuthorization(authorizationRecord);
    Assert.assertEquals(true, result);
  }

  @Test
  public void testInvalidTransAmountWithoutZip() {
    var authorizationRecord = new AuthorizationRecord();
    authorizationRecord.setTransactionAmount("100000");
    authorizationRecord.setExpirationDate(YearMonth.of(9999, 11).atDay(1));
    var result = RecordValidator.validAuthorization(authorizationRecord);
    Assert.assertEquals(false, result);
  }

  @Test
  public void testValidDate() {
    var result = RecordValidator.isValidDate(YearMonth.of(9999, 11).atDay(1));
    Assert.assertEquals(true, result);
  }

  @Test
  public void testInvalidDate() {
    var result = RecordValidator.isValidDate(YearMonth.of(1111, 11).atDay(1));
    Assert.assertEquals(false, result);
  }
}
