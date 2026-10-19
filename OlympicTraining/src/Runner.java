class Runner extends Athlete {
    private int age;
    private int height;
    private boolean shoesAreOn = false;

    public Runner(int age, int height) {
        super(0, 0, 0, 0);
        this.age = age;
        this.height = height;
    }

    public Runner(int age, int height, int x, int y) {
        super(x, y, 0, 0);
        this.age = age;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void move(int steps) {
        if (shoesAreOn) {
            for (int i = 0; i < steps; i++) {
                move();
            }
        } else {
            IO.println("Shoes aren't on - cannot run");
        }
    }

    public void toggleShoes() {
        shoesAreOn = !shoesAreOn;
    }

    public boolean getShoeStatus() {
        return shoesAreOn;
    }
}

public void main(String[] args) {
    Runner harrison = new Runner(15, 67, 1, 1);
    harrison.move(5);
    harrison.toggleShoes();
    harrison.move(5);
    IO.println("runner is " + harrison.getAge() + " years old and " + harrison.getHeight() + " inches tall!\nAre shoes on? - " + harrison.getShoeStatus());
}