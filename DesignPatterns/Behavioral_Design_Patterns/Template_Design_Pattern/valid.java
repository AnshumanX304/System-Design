package DesignPatterns.Behavioral_Design_Patterns.Template_Design_Pattern;

abstract class BeverageWithHook {
    final void prepareRecipe() {  
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    void boilWater() {
        System.out.println("Boiling water !!");
    }

    void pourInCup() {
        System.out.println("Pouring in cup !!");
    }

    abstract void brew();
    abstract void addCondiments();

    boolean customerWantsCondiments() {
        return true;
    }
}

class CustomCoffee extends BeverageWithHook {
    @Override
    void brew() {
        System.out.println("Brewing coffee...");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }

    // this customer doesn't want condiments
    @Override
    boolean customerWantsCondiments() {
        return false;
    }
}

class CustomTea extends BeverageWithHook {
    @Override
    void brew() {
        System.out.println("Steeping the tea...");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon...");
    }

    // this customer wants condiments
    @Override
    boolean customerWantsCondiments() {
        return true;
    }
}

public class valid {
    public static void main(String[] args) {
        BeverageWithHook coffee = new CustomCoffee();
        System.out.println("Making custom coffee...");
        coffee.prepareRecipe();

        System.out.println("\nMaking custom tea...");
        BeverageWithHook tea = new CustomTea();
        tea.prepareRecipe();
    }
}
