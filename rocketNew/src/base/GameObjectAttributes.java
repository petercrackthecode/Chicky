package base;

public interface GameObjectAttributes<D extends GameObject> { // D must be an extended type from GameObject
	void run(D gameObject);
//	int a= 4;
//	public static void main(String[] args)	{
//		System.out.println("Hello world");
//		int m= 4;
//	}
}
