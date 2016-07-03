package leetcode;

public class SingletonStaticClass {
	/*
	 * 1. 首先Singleton的object是存在heap里面而static object class是在stack里面. 
	 * 2. 其次你可以clone一个Singleton但是你不能clone一个static class.
	 * 3. 第三Singleton class本身是follow object oriented principles但是static class并不是，
	 * 4. 第四点是我们可以在singleton上定义一个interface但是不能在static class上定义interface.
	 * 5. 第五点Jon Skeet提到的是a singleton allows access to a single created instance -
	 * 那么这个singleton的instance可以被作为参数传到其他的method里面并且作为一个normal object。
	 */
}
