import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
	// The delay (ms) corresponds to 20 updates/second (hz)
	private final int delay = 50;
	// The timer is started with a listener (see below) that executes the statements
	// each step between delays.
	private Timer timer = new Timer(delay, new TimerListener());

	// The frame that represents this instance View of the MVC pattern
	CarView frame;

	ArrayList<Car> cars = new ArrayList<>();

	public void startTimer() {
		this.timer.start();
	}

	/* Each step, the TimerListener moves all cars in the list and tells the
	* view to update its images.
	*/
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    for (Car car : cars) {
			car.move();

				// limits
				int maxX = frame.drawPanel.getWidth() - 100;
				int maxY = frame.drawPanel.getHeight() - 60;

				// collision
				if (car.getCurrentX() > maxX){
					car.setPosition(maxX, car.getCurrentY());
					car.reverseDirection();

				} else if (car.getCurrentX() < 0){
					car.setPosition(0, car.getCurrentY());
					car.reverseDirection();
				}

				if (car.getCurrentY() > maxY){
					car.setPosition(car.getCurrentX(),maxY);
					car.reverseDirection();
				} else if (car.getCurrentY() < 0){
					car.setPosition(car.getCurrentX(), 0);
					car.reverseDirection();
				}


			// repaint() calls the paintComponent method of the panel
			frame.drawPanel.revalidate();
			frame.drawPanel.repaint();
		    }
		}
	}

	void gas(int amount) {
		double gas = ((double) amount) / 100;
		for (Car car : cars) {
		    car.gas(gas);
		}
	}
    void turboOn(){
		for (Car car: cars){
			if (car instanceof Saab95 saab){
				saab.setTurboOn();
			}

		}
	}
 	void turboOff(){
		for (Car car: cars){
			if (car instanceof Saab95 saab){
				saab.setTurboOff();
			}
		}
	}
	void brake(int amount) {
		double brake = ((double) amount) / 100;
		for (Car car : cars) {
			car.brake(brake);
		}
	}

	void startAllCars() {
		for (Car car : cars) {
			car.startEngine();
		}
	}

	void stopAllCars() {
		for (Car car : cars) {
			car.stopEngine();
		}
	}

	void turnLeft() {
		for (Car car : cars) {
			car.turnLeft();
		}
	}

	void turnRight() {
		for (Car car : cars) {
			car.turnRight();
		}
	}
}
