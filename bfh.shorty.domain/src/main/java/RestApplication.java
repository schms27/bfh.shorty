import config.AppConfig;
import config.ServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by simon.schmid on 02.11.2017.
 */
public class RestApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        Class[] classes = new Class[1];
        classes[0] = AppConfig.class;
        return classes;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class[] classes = new Class[1];
        classes[0] = ServletConfig.class;
        return classes;
    }

    @Override
    protected String[] getServletMappings() {
        String[] mapping = new String[1];
        mapping[0] = "/rest/*";
        return mapping;
    }
}
