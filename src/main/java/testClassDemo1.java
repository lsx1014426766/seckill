
public class testClassDemo1 {
  public static void main(String[] args) {
	//类可以创建自己的对象new的方式，同样的类又是谁的对象呢？Class 跨越一级的关系 类类型
	//创建Class的方式有三：
	  Foo foo = new Foo();
	  Class c1=Foo.class;
	  Class c2=	foo.getClass();
	  Class c3=null;
	  try {
		c3=Class.forName("");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
class Foo{
	
}
