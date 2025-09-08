package DesignPatterns.Structural_Design_Patterns.Decorator_Design_Pattern;

interface ICharacter {
    public String getAbilities();
}

class Mario implements ICharacter {
    @Override
    public String getAbilities() {
        return "I am a normal Mario";
    }
}

abstract class CharacterDecorator implements ICharacter {
    protected ICharacter character;

    public CharacterDecorator(ICharacter character) {
        this.character = character;
    }

    @Override
    public abstract String getAbilities();
}

class HeightUpDecorator extends CharacterDecorator {
    public HeightUpDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with heightup,";
    }
}

class SpeedupDecorator extends CharacterDecorator {
    public SpeedupDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with speedup,";
    }
}

class GunupDecorator extends CharacterDecorator {
    public GunupDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with gun,";
    }
}

public class valid {
    public static void main(String[] args) {
        ICharacter mario = new Mario();
        mario = new HeightUpDecorator(mario);
        mario = new GunupDecorator(mario);
        mario = new SpeedupDecorator(mario);

        System.out.println(mario.getAbilities());
    }
}
