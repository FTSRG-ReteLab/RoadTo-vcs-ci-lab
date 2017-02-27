package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int accelerateLimit = 15;
	public Table<Integer, Integer, Long> tahograph = HashBasedTable.create();

	@Override
	public void followSpeed() {
		if ((referenceSpeed+step) < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}
		tahograph.put(referenceSpeed, step, System.currentTimeMillis());

		enforceSpeedLimit();
	}

	@Override
	public boolean getTable() {
	
		return tahograph.isEmpty();
	}
	
	@Override
	public int getAccLimit() {
		return accelerateLimit;
	}	
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
