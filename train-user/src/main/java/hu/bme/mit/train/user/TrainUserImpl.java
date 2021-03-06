package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;
	boolean alarmFlag;

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
		alarmFlag = false;
	}

	@Override
	public boolean getAlarmFlag() {
		return alarmFlag;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;
		controller.setJoystickPosition(joystickPosition);
		
		if(joystickPosition>controller.getAccLimit())
		{
			alarmFlag = true;
		}
	}

}
