package board.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import board.intercepter.LoggerInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	// LoggerInterceptor 클래스를 스프링 빈으로 등록하는 과정
	
	// 원래는 addPathPatterns()메서드와 excludePathPatterns() 메서드로 요청 주소의 패턴과 제외할 요청 주소의 패턴을 지정하여 선택적으로 적용할 수 있다.
	// 하지만 여기서는 모든 요청에 대해서 인터셉터를 적용하기 때문에 특별한 패턴은 지정하지 않았다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
	}

}
