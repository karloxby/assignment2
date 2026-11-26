
import java.awt.*;

public abstract class Car implements Movable {
	
	private double enginePower;
	private int nrDoors;
	private Color color;
	private String modelName;
	private double currentSpeed;
	private double x;
	private double y;
	private String direction;

	public Car(double enginePower, int nrDoors, Color color, String modelName) {
		this.enginePower = enginePower;
		this.nrDoors = nrDoors;
		this.color = color;
		this.modelName = modelName;
		this.direction = "up";
	}

	public double getEnginePower() {
		return enginePower;
	}
	
	public int getNrDoors() {
		return nrDoors;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color clr) {
		color = clr;
	}

	public String getModelName() {
		return modelName;
	}

	public void startEngine() {
		currentSpeed = 0.1;
	}
	public void stopEngine() {
		currentSpeed = 0;
	}
	
	public double getCurrentSpeed() {
		return currentSpeed;
	}

	// every subclass must implement this method
	public abstract double speedFactor();

	private void incrementSpeed(double amount) {
		currentSpeed = getCurrentSpeed() + speedFactor() * amount;

		double maxSpeed = getEnginePower();
		if (currentSpeed > maxSpeed) {
			currentSpeed = maxSpeed;
		}
	}
	private void decrementSpeed(double amount) {
		currentSpeed = getCurrentSpeed() - speedFactor() * amount;

		if (currentSpeed < 0) {
			currentSpeed = 0;
		}
	}
	
	// limits how much speed can be increased or decreased
	private double clamp(double amount) {
		if (amount > 1) {
			amount = 1.0d;
		} else if (amount < 0) {
			amount = 0.0d;
		}

		return amount;
	}

	public void gas(double amount) {
		incrementSpeed(clamp(amount));
	}
	public void brake(double amount) {
		decrementSpeed(clamp(amount));
	}

	public double getCurrentX() {
		return x;
	}
	public double getCurrentY() {
		return y;
	}
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String getCurrentDirection() {
		return direction;
	}

	public void turnLeft() {
		switch (direction) {
			case "up":
				direction = "left";
				break;
			case "left":
				direction = "down";
				break;
			case "down":
				direction = "right";
				break;
			case "right":
				direction = "up";
				break;
		}
	}

	public void turnRight() {
		switch (direction) {
			case "up":
				direction = "right";
				break;
			case "right":
				direction = "down";
				break;
			case "down":
				direction = "left";
				break;
			case "left":
				direction = "up";
				break;
		}
	}

	public void move() {
		switch (direction) {
			case "up":
				y = y - currentSpeed;
				break;
			case "right":
				x = x + currentSpeed;
				break;
			case "down":
				y = y + currentSpeed;
				break;
			case "left":
				x = x - currentSpeed;
				break;
		}
	}

	public void reverseDirection(){
		turnRight();
		turnRight();
	}
}

