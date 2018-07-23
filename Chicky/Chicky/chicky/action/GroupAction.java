package action;

import java.util.Arrays;
import java.util.List;

import base.GameObject;

public class GroupAction implements Action{
	private List<Action> actions;
	public GroupAction(Action... actions)	{
		this.actions= Arrays.asList(actions);
	}

@Override
public boolean run(GameObject owner)	{
	this.actions.removeIf(action -> action.run(owner));
	return this.actions.isEmpty();
}

@Override
public void reset()	{
	
}

}