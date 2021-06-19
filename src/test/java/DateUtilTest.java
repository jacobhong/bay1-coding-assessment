import com.bay1.assessment.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

  private DateUtil dateUtil = new DateUtil();

  @Test
  public void testDateValid() {
    var date = dateUtil.dateFromString("1125");
    Assert.assertEquals("2025-11-01", date.toString());
  }
}
