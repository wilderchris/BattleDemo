package com.wilder.bean;

import java.util.Objects;

public class Hero {

	private int id;
	private String name;
	private String type;
	private int attack;
	private int defense;
	private int magic;
	private int health;

	public Hero() {

	}

	public Hero(int id, String name, String type, int attack, int defense, int magic, int health) {
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Hero [name=" + name + ", type=" + type + ", attack=" + attack + ", defense=" + defense
				+ ", magic=" + magic + ", health=" + health + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attack, defense, health, id, magic, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hero other = (Hero) obj;
		return attack == other.attack && defense == other.defense && health == other.health && id == other.id
				&& magic == other.magic && Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}

	

}
