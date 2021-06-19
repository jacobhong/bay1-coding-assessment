import com.bay1.assessment.util.BitmapUtil;
import org.junit.Assert;
import org.junit.Test;

public class BitmapUtilTest {
  @Test
  public void testEncodeBitmap() {
    var result = BitmapUtil.encodeBitmap("11100000");
    Assert.assertEquals("e0", result);
  }

  @Test
  public void testDecodeBitmap() {
    var result = BitmapUtil.decodeBitmap("e0");
    Assert.assertEquals("11100000", result);
  }
}
