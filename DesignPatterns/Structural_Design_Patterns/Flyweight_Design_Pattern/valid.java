package DesignPatterns.Structural_Design_Patterns.Flyweight_Design_Pattern;
import java.util.*;

class AsteroidFlyweight {
    private int height;
    private int weight;
    private String color;
    private String texture;
    private String material;

    AsteroidFlyweight(int height, int weight, String color, String texture, String material) {
        this.height = height;
        this.weight = weight;
        this.color = color;
        this.texture = texture;
        this.material = material;
    }

    int getSize() {
        return Integer.BYTES * 2 + 32 * 3;
    }

    void render(int x, int y, int velocityX, int velocityY) {
        System.out.println("Rendering asteroid [" +height + " " + weight + " " + color + ", " + texture + ", " + material +
                "] at (" + x + "," + y + ") moving (" + velocityX + "," + velocityY + ")");
    }
}

class AsteroidFactory {
    private Map<String, AsteroidFlyweight> asteroidMap = new HashMap<>();

    AsteroidFlyweight getAsteroid(int height, int weight, String color, String texture, String material) {
        String key = height + "|" + weight + "|" + color + "|" + texture + "|" + material;
        if (!asteroidMap.containsKey(key)) {
            asteroidMap.put(key, new AsteroidFlyweight(height, weight, color, texture, material));
        }
        return asteroidMap.get(key);
    }

    int getPoolSize() {
        return asteroidMap.size();
    }
}

class AsteroidContext {
    AsteroidFlyweight flyweight;
    int posx, posy;
    int velocityx, velocityy;

    AsteroidContext(AsteroidFlyweight flyweight, int posx, int posy, int velocityx, int velocityy) {
        this.flyweight = flyweight;
        this.posx = posx;
        this.posy = posy;
        this.velocityx = velocityx;
        this.velocityy = velocityy;
    }

    void draw() {
        flyweight.render(posx, posy, velocityx, velocityy);
    }
}

class SpaceGameAsteroid {
    private List<AsteroidContext> asteroids = new ArrayList<>();

    public void spawnAsteroids(int count) {
        List<String> colors = Arrays.asList("Red", "Blue", "Gray");
        List<String> textures = Arrays.asList("Rocky", "Metallic", "Icy");
        List<String> materials = Arrays.asList("Iron", "Stone", "Ice");
        int[] sizes = {25, 35, 45};

        AsteroidFactory factory = new AsteroidFactory();

        for (int i = 0; i < count; i++) {
            int type = i % 3;
            AsteroidFlyweight flyweight = factory.getAsteroid(
                    sizes[type],
                    sizes[type],
                    colors.get(type),
                    textures.get(type),
                    materials.get(type)
            );
            AsteroidContext asteroid = new AsteroidContext(flyweight,
                    100 + i * 50,
                    200 + i * 30,
                    1,
                    2);
            asteroids.add(asteroid);
        }

        System.out.println("Total flyweights created: " + factory.getPoolSize());
    }

    public void render() {
        for (AsteroidContext asteroid : asteroids) {
            asteroid.draw();
        }
    }
}

public class valid {
    public static void main(String[] args) {
        int asteroidCount = 1000;
        SpaceGameAsteroid game = new SpaceGameAsteroid();
        game.spawnAsteroids(asteroidCount);
        game.render();
    }
}
