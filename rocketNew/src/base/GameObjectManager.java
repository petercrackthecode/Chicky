package base;
import java.awt.*;
import java.util.ArrayList;

import game.enemy.Enemy;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager();

    private ArrayList<GameObject> list = new ArrayList<>();
    private ArrayList<GameObject> tempList = new ArrayList<>();

    private GameObjectManager() {

    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list
        		.stream()
        		.filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list
        .stream()
        .filter(gameObject -> gameObject.isAlive)
        .filter(gameObject -> !(gameObject instanceof Player))
        //.filter(gameObject -> gameObject instanceof Enemy)
        .forEach(gameObject -> gameObject.render(graphics));
        
        this.list
        .stream()
        .filter(gameObject -> gameObject.isAlive)
        .filter(gameObject -> gameObject instanceof Player)
        .filter(gameObject -> gameObject.life > 3)
        .forEach(gameObject -> gameObject.render(graphics));
    }

    public Player findPlayer() {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> gameObject.isAlive)
                .findFirst()
                .orElse(null);
    }
    
    public Enemy checkCollision(BulletPlayer bulletPlayer) {
		return (Enemy) this.list.stream().filter(gameObject -> gameObject.isAlive)
				.filter(gameObject -> gameObject instanceof Enemy)
				.filter(gameObject -> {
			BoxCollider other = ((Enemy) gameObject).boxCollider;
			return bulletPlayer.boxCollider.checkCollision(other);
		}).findFirst().orElse(null);
    }
    
}
