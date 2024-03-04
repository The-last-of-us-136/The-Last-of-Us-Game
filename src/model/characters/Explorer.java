package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}
	
	public String toString(){
		String s="Name: "+getName()+"\n"+"Type: Explorer"+"\n"+"HP: "+getCurrentHp()+"\n"+"Attack damage: "+getAttackDmg()+"\n"+"Max Actions: "+getMaxActions()+"\n"+"Actions: "+getActionsAvailable();
		return s;
	}
	public String toString2(){
		String s="<html><p>Name: "+getName()+"</p>"+"<p>Type: Explorer</p>"+"<p>HP: "+getCurrentHp()+"</p>"+"<p>Attack damage: "+getAttackDmg()+"</p>"+"<p>Max Actions: "+getMaxActions()+"</p>"+"<p>Actions: "+getActionsAvailable()+"</p></html>";
		return s;
	}
	
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		super.useSpecial();
		for(int i = 0; i < Game.map.length; i++) {
			for(int j = 0; j < Game.map[i].length; j++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}

}
