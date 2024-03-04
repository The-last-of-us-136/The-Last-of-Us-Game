package model.characters;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage, maxActions);
	}
	
	public String toString(){
		String s="Name: "+getName()+"\n"+"Type: Fighter"+"\n"+"HP: "+getCurrentHp()+"\n"+"Attack damage: "+getAttackDmg()+"\n"+"Max Actions: "+getMaxActions()+"\n"+"Actions: "+getActionsAvailable();
		return s;
	}
	public String toString2(){
		String s="<html><p>Name: "+getName()+"</p>"+"<p>Type: Fighter</p>"+"<p>HP: "+getCurrentHp()+"</p>"+"<p>Attack damage: "+getAttackDmg()+"</p>"+"<p>Max Actions: "+getMaxActions()+"</p>"+"<p>Actions: "+getActionsAvailable()+"</p></html>";
		return s;
	}

}
