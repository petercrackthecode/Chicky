package base;

public interface GameObjectAttribute<T extends GameObject> {
	void run(T gameObject);
}
