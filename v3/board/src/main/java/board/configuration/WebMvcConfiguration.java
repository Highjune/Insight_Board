package board.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
	
	
	// 인코딩
	// 아래는 2개 메서드는 스프링 부트 2.1.x 버전 아래에서만 추가하면 된다. 스프링 부트 2.1.x버전에는 이미 인코딩 필터가 적용되어 있으므로 안해도 됨. 
//	@Bean
//	public Filter characterEncodingFilter() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter(); //CharacterEncodingFilter는 스프링제공 클래스로 웹에서 주고받는 데이터의 헤더값을 utf-8로 인코딩 해준다
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		
//		return characterEncodingfilter;
//	}
//	@Bean
//	public HttpMessageConverter<String> responseBodyConverter(){
//		return new StringHttpmessageConverter(Charset.forName("UTF-8")); //StringHttpmessageConverter는 @ResponseBody를 이용하여 결과 출력할 떄 그 결과를 UTF-8로 설정한다.
//	}
	
}
