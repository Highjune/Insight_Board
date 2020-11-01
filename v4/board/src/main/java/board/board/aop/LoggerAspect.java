package board.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggerAspect {	
	// 공통적으로 적용할 로그 기능을 LoggerAspect라는 외부 클래스에 정의하고 애플리케이션의 실행 시점에 기능이 삽입되어 실행되었다.
	// 이렇게 AOP를 이용해서 각각의 관심을 분리하고 기존 로직의 변화업이 원하는 시점에 코드를 삽입할 수 있다.
	private Logger log = LoggerFactory.getLogger(this.getClass());

	//@Around는 AOP의 어드바이스 5종류 중 1개(동작하는 시점에 따라 다름)
	@Around("execution(* board..controller.*Controller.*(..)) or "
			+ "execution(* board..service.*Impl.*(..)) or "
			+ "execution(* board..mapper.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName(); // name은 board.board.controller.BoardController, board.board.service.BoardServiceImpl, board.board.mapper.BoardMapper 
		System.out.println("--------here------" + name);
		if (name.indexOf("Controller") > -1) {
			type = "Controller  \t:  ";
		} else if (name.indexOf("Service") > -1) {
			type = "ServiceImpl  \t:  ";
		} else if (name.indexOf("Mapper") > -1) {
			type = "Mapper  \t\t:  ";
		}
		log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}



