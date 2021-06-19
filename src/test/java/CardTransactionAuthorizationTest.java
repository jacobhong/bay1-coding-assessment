import com.bay1.assessment.AuthorizationParser;
import org.junit.Assert;
import org.junit.Test;

public class CardTransactionAuthorizationTest {
  private AuthorizationParser authorizationParser = new AuthorizationParser();
  private String OK_RECORD = "0100e016411111111111111112250000001000";
  private String DE_RECORD = "0100e016401288888888188112250000011000";
  private String ERR_RECORD = "01006012250000001000";

  @Test
  public void testOkRecord() {
    var result = authorizationParser.parseRecord(OK_RECORD);
    Assert.assertEquals("0110f016411111111111111112250000001000OK", result);
  }

  @Test
  public void testDERecord() {
    var result = authorizationParser.parseRecord(DE_RECORD);
    Assert.assertEquals("0110f016401288888888188112250000011000DE", result);
  }

  @Test
  public void testERRecord() {
    var result = authorizationParser.parseRecord(ERR_RECORD);
    Assert.assertEquals("01107012250000001000ER", result);
  }
}
