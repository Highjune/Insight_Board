package board.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	// 처리할 예외 종류 설정. 여기서는 기능 확인하기 위해서 Exception 클래스 설정.
	// 그런데 실제로는 다양한 예외처리하기 위한 각각의 예외처리 필요.(ex. NullPointerException) 등
	// 코드 상 Exception.class 이 제일 밑에 있어야 예외 정상 처리 가능.
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class) 
	public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception){
		ModelAndView mv = new ModelAndView("/error/error_default"); //예외 처리시 보여줄 화면 지정
		mv.addObject("exception", exception);
		
		log.error("defaultExceptionHandler", exception);
		
		return mv;
	}
}


