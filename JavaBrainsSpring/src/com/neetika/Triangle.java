package com.neetika;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Triangle implements Shape, ApplicationContextAware, BeanNameAware, InitializingBean, DisposableBean {

	Point pointA;
	Point pointB;
	Point pointC;
	private ApplicationContext applicationContext;

	@Override
	public void draw()  {
		System.out.println("Drawing Triangle");
		System.out.println("Point A - " + this.pointA.getX() + ", " + this.pointA.getY());
		System.out.println("Point B - " + this.pointB.getX() + ", " + this.pointB.getY());
		System.out.println("Point C - " + this.pointC.getX() + ", " + this.pointC.getY());
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {

		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("Bean name is - "+arg0);
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Disposable destroy() method called");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing init() method called");
		
	}
	

	public void myInit() throws Exception {
		System.out.println("myInit() method called");
		
	}
	
	public void myDestroy() throws Exception {
		System.out.println("Destroy() method called");
		
	}

	@Override
	public void testLoggingAspect(String myName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String testLoggingAspectError(String myName) {
		return null;
		
	}
	

	@Override
	public void testingCustomAnnotation() {
		// TODO Auto-generated method stub
		
	}
}
