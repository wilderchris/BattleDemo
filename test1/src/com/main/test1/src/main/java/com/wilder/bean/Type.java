package com.wilder.bean;

import java.util.Objects;

public class Type {

	private int id;
	private String type;
	private int attack;
	private int defense;
	private int magic;
	private int health;

	public Type() {

	}

	public Type(int id, String type, int attack, int defense, int magic, int health) {
		this.id = id;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.magic = magic;
		this.health = health;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + ", attack=" + attack + ", defense=" + defense
				+ ", magic=" + magic + ", health=" + health + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attack, defense, health, id, magic, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return attack == other.attack && defense == other.defense && health == other.health && id == other.id
				&& magic == other.magic && Objects.equals(type, other.type);
	}

	
	

}
