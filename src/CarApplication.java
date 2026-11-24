
void main() {
	// Instance of this class
	CarController cc = new CarController();

	cc.cars.add(new Volvo240());
	
	// Start a new view and send a reference to the controller
	cc.frame = new CarView("CarSim 1.0", cc);
	cc.startTimer();
}

