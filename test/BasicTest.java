import models.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import play.test.UnitTest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * A few tests for PropertyUtils.copyProperties. See this model: https://github.com/ripper234/Play-doesn-t-work-with-PropertyUtils.copyProperties/blob/master/app/models/User.java
 */
public class BasicTest extends UnitTest {



    @Test
    public void getBeanInfo_manualProperty() throws IntrospectionException {

        // This test passes

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        List<String> propertyNames = new ArrayList<String>();
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            propertyNames.add(descriptor.getDisplayName());
        }

        assertTrue(propertyNames.contains("manualProperty"));
    }

    @Test
    public void beanUtilsCopy_manualProperty() throws Exception {

        // This test passes

        User source = new User();
        source.setManualProperty("aaa");
        User copy = new User();
        PropertyUtils.copyProperties(copy, source);
        assertEquals("aaa", copy.getManualProperty());
    }

    @Test
    public void getBeanInfo_autoPlayProperty() throws IntrospectionException {

        // This test fails

        Introspector.flushCaches();

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        List<String> propertyNames = new ArrayList<String>();
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            propertyNames.add(descriptor.getDisplayName());
        }

        assertTrue(propertyNames.contains("somefield"));
    }

    @Test
    public void beanUtilsCopy_autoPlayProperty() throws Exception {

        // This test fails

        Introspector.flushCaches();

        User source = new User();
        source.somefield = "aaa";
        User copy = new User();
        PropertyUtils.copyProperties(copy, source);
        assertEquals("aaa", copy.somefield);
    }
}
