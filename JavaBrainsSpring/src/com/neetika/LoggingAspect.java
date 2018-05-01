package com.neetika;

import java.util.Calendar;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	/**
	 * 3 Pointcuts Expressions:
	 * args(), execution(), within
	 * 
	 * args(String) - this will execute all methods having 1 argument as String
	 * 
	 * Advice Types:
	 * @Before, @After, @AfterThrowing, @AfterReturning, @Around
	 * 
	 * joinPoint descripion: joinPoint is the point in code where I can apply the advice, in spring AOP joinPoint is always method. So advice is applied only to methods in Spring AOP.
	 * In aspectJ advice can be applied to member variables.
	 * 
	 * 
	 * @After Advice type is executed no matter what is the result of the method, even if execption is thrown,@After is executed.It is similar to AfterFinaly
	 * @AfterReturning is executed only when method is successfully completed, and no exeception is met
	 * @AfterThrowing is executed only when method is caught into Exception
	 * 
	 * execution(* Circle.draw()) 
	 * execution(public void draw()) 
	 * execution(public * com.neetika.Circle.draw()) 
	 * execution(public void com.neetika.*.draw()) 
	 * execution(public void dr*()) - For all methods with * starting name as 'dr' with return type as 'VOID'
	 * execution(public * dr*()) - For all methods with starting name as 'dr' with ANY return type
	 * execution(* dr*()) - For all methods with starting name as 'dr' with ANY * return type and irrespetive whether it is public/private
	 * 
	 * Arguments - 
	 * execution(* dr*()) - Run for methods with NO argument
	 * execution(* dr*(*)) - Run for methods with ONE or More arguments
	 * execution(* dr*(..)) - Run for methods with ZERO or More arguments
	 * 
	 * Note
	 * 1- If we have same advice for multiple pointcut expressions(multiple methods in say different classes for which we cannot have a common pointcut) 
	 * we can use && operator. However, if the list of expresson is huge we much use Custom Annotation Advice eg @Around("@annotation(com.neetika.LoggableAnnotation)").
	 * here we need to use @LoggableAnnotation on everymethod we want to include in advise
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * This method executes code before and after target method execution; that y around
	 * 
	 *  One more plus point is that we can modify the returnObject and pass it to actual code which is not possible with AfterReturning
	 * 
	 * @param proceedingJoinPoint - COMPULSARY ARGUMENT
	 * 
	 */
	//@Around("allDrawMethods()")
	@Around("@annotation(com.neetika.LoggableAnnotation)") //Now use @LoggableAnnotation in all target methods 
	public Object loggingAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("");
		Object returnObject = null;//Can be String or any other object 
		try {
			System.out.println("LoggingAspect :: loggingAroundAdvice :: Before targetted method executed");
			returnObject = proceedingJoinPoint.proceed();//POint where we want targetted method to execute; this also means that we can decide whether or not execute the target method
			System.out.println("LoggingAspect :: loggingAroundAdvice :: After targetted method executed");
		} catch (Throwable e) {
			System.out.println("LoggingAspect :: loggingAroundAdvice :: Got Exception");
			e.printStackTrace();
		}
		
		System.out.println("LoggingAspect :: loggingAroundAdvice :: After finally");
		return returnObject;
	}
	

	
	
	/**
	 * This method will run to catch the exception has occurred in code; method will be called oly when exception has occurred so an handle to the expection object is provided to us
	 * @param name
	 * @param ex optional; it can be Exception object: we can use this method to log exceptions at one place
	 */
	//@AfterThrowing("args(name)")
	@AfterThrowing(pointcut="args(name)", throwing="ex")
	public void logErrors(String name, RuntimeException ex){
		System.out.println("");
		System.out.println("LoggingAspect :: AfterThrowing :: "+ex);
	}
	
	/**
	 * This method to be called when a method with String parameter is called and with return type as String
	 * @param name
	 * @param neetikaReturnTest  - This is Optional(to get the return type)
	 */
	//@AfterReturning("args(name)")
	@AfterReturning(pointcut="args(name)", returning="neetikaReturnTest")
	public void logReturningErrors(String name, Object neetikaReturnTest){
		
		System.out.println("");
		System.out.println("LoggingAspect :: AfterReturning :: Argument name - "+name);
		System.out.println("LoggingAspect :: AfterReturning :: Returned Value - "+neetikaReturnTest);
	}
	
	
	// @Before("execution(* dr*(..))")
	@Before("allDrawMethods() && allClassMethods()")//Using 2 pointcuts together; this advice wll be applicable only when both pointcuts satisfy
	public void loggingBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("LoggingAspect.Before() : First Advice : Executing " + joinPoint.getSignature().getName()
				+ "() at " + Calendar.getInstance().getTime());
		
		Circle circle = (Circle)joinPoint.getTarget();//Powerful handle to the actual object, we can use this object now in aspect class
	}

	// @Before("execution(* dr*(..))")
	@Before("allDrawMethods()")
	public void loggingBeforeAdvice_2(JoinPoint joinPoint) {
		System.out.println("LoggingAspect.Before()  : Second Advice : Executing " + joinPoint.getSignature().getName()
				+ "() at " + Calendar.getInstance().getTime());
	}
	
	@Before("args(String)")// Method that takes string as argument has been called
	public void loggingArgumentPointcut(JoinPoint joinPoint) {
		System.out.println("LoggingAspect.Before()  : loggingArgumentPointcut : Method that takes string as argument has been called");
	}
	
	@Before("args(name)")// Method that takes string as argument has been called, this way we can access the passed argumet as well
	public void loggingArgumentPointcut_1(String name) {
		System.out.println("LoggingAspect.Before()  : loggingArgumentPointcut : Method that takes string as argument has been called - "+name);
	}


	// When same pointcut is required for more than 1 method
	@Pointcut("execution(* dr*(..))")
	public void allDrawMethods() {

	}

	@Pointcut("within(com.neetika.Circle)") //For All the methods of Circle class
	public void allCircleMethods() {

	}
	
	@Pointcut("within(com.neetika.*)") //For All the methods of All classes under com.neetika
	public void allClassMethods() {

	}
	
	@Pointcut("within(com.neetika..*)") //For All the methods of All classes under com.neetika a well as under any subpackage too
	public void allPackageMethods() {

	}


	@After("execution(public void draw())")
	public void loggingAfterAdvice(JoinPoint joinPoint) {
		System.out.println("LoggingAspect.After() : Executed " + joinPoint.getSignature().getName() + "() at "
				+ Calendar.getInstance().getTime());
	}

}
