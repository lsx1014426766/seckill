package com.test.class1;

public class testClassDemo1 {
  public static void main(String[] args) {
	/*类可以创建自己的对象new的方式，同样的类又是谁的对象呢？Class 跨越一级的关系 类类型
	 * 任何一个类都是Class的实例对象，这个实例对象有3种表示方式
	 * 官网 c1,c2表示foo的类类型
	 * 反之，也可以通过类类型c1 or c2 or c3 创建foo对象
	 * 类是java.lang.Class类的实例对象
	 * 一个类只对应一个类类型
	 * 
	 * 关于动态加载类
	 * Class.forName("类的全称")
		表示：1动态加载类 2类类型
		编译：静态加载类  new属于静态加载类   在编译时会加载所有用到的类
		运行：动态加载类  只在运行期才会去关心里面的类有没有
		解决：
			   当设计实现时，使用接口来实现具体类，Class c=Class.forName("com.entity.Office");
			  OfficeBetter oa=(OfficeBetter)c.newInstance(); 用接口来接收返回
	*/
	//创建Class的方式有三：
	  Foo foo = new Foo();
	  Class c1=Foo.class;//任何一个类都有一个隐含的静态成员变量class
	  Class c2=	foo.getClass();
	  
	  Class c3=null;
	  try {
		c3=Class.forName("com.test.class1.Foo");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(c1==c2);
	  try {
		Foo newInstance = (Foo)c1.newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
class Foo{
	
}
