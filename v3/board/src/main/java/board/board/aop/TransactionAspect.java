package board.board.aop;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

// aop를 이용해서 트랜잭션 설정.
// 이렇게 aop를 이용해서 트랜잭션 설정하면 새로운 클래스나 메서드가 추가될 때 따로 어노테이션 붙이지 않아도 자동적으로 트랜잭션 처리가 된다.
@Configuration
public class TransactionAspect {
	//트랜잭션 설정시 사용되는 설정값을 상수로 선언
	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* board..service.*Impl.*(..))"; //포인트컷 - 비즈니스 로직이 수행되는 모든 ServiceImpl 클래스의 모든 메서드 지정
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor transactionAdvice(){
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		//transaction 이름 설정 - 추후에 트랜잭션 모니터에서 트랙재션의 이름으로 확인 가능
		transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);
		// 롤백을 하는 룰(rule) 설정. 여기서는 모든 예외가 일어나면 롤백이 수행되도록 지정
		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		source.setTransactionAttribute(transactionAttribute);
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor transactionAdviceAdvisor(){
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		//aop의 pointcut 설정
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
