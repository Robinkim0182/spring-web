package kr.ac.cnu.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();

        converters.add(new MarshallingHttpMessageConverter(xstreamMarshaller, xstreamMarshaller));
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
