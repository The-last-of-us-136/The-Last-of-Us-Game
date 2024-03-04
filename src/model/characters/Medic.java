package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {

	public Medic(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}
	
	public String toString(){
		String s="Name: "+getName()+"\n"+"Type: Medic"+"\n"+"HP: "+getCurrentHp()+"\n"+"Attack damage: "+getAttackDmg()+"\n"+"Max Actions: "+getMaxActions()+"\n"+"Actions: "+getActionsAvailable();
		return s;
	}
	public String toString2(){
		String s="<html><p>Name: "+getName()+"</p>"+"<p>Type: Medic</p>"+"<p>HP: "+getCurrentHp()+"</p>"+"<p>Attack damage: "+getAttackDmg()+"</p>"+"<p>Max Actions: "+getMaxActions()+"</p>"+"<p>Actions: "+getActionsAvailable()+"</p></html>";
		return s;
	}
	

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if (getTarget() instanceof Zombie)
			throw new InvalidTargetException("You can only cure fellow heroes.");
		if (!checkDistance())
			throw new InvalidTargetException("You are only able to heal adjacent targets.");
		super.useSpecial();
		getTarget().setCurrentHp(getTarget().getMaxHp());
	}
}



