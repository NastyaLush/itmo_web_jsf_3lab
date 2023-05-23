


import com.example.demo.validation.Validation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
//import org.mockito.InjectMocks;
//import org.testng.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleTest {
    @InjectMocks
    private Validation app = new Validation();
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Validation.class.getDeclaredMethod("isInRectangle", double.class, double.class, double.class);
        method.setAccessible(true);
        Assert.assertFalse((Boolean) method.invoke(app, 3,3,3));
    }

}
