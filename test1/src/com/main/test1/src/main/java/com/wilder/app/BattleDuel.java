package com.wilder.app;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.wilder.bean.Hero;
import com.wilder.bean.Type;
import com.wilder.data.postgres.HeroPostgres;
import com.wilder.data.postgres.TypePostgres;

public class BattleDuel {// needs refactoring and adjustment for enemy attack and player attack going below 0 1st

	public static void main(String[] args) {
		final int STATS = 30;
		int contrib = 0;
		TypePostgres typePostgres = new TypePostgres();

		HeroPostgres heroPostgres = new HeroPostgres();
		int heroChoice = 0;
		Hero playerHero = new Hero();

		System.out.println("Welcome to BattleDuel v1");
		System.out.println("First choose your base class");

		System.out.println("1. Tank  = Health=100; Attack= 20; Defense= 20");
		System.out.println("2. Healer= Health=100; Attack= 15; Defense= 15, but heals every up to 10 every turn");
		System.out.println("3. Mage  = Health=100; Attack= 10; Defense= 10, but adds a magic attack every turn up to 15 HP");

		System.out.print("Pick your Heros Base Class: ");
		Scanner sc = new Scanner(System.in);
		heroChoice = sc.nextInt();
		System.out.println();
		System.out.print("Next name your Hero: ");
		String heroName = sc.next();
		System.out.println();
		System.out.println(heroChoice + "  " + heroName);

		Set<Type> types = new HashSet<Type>();
		types = typePostgres.getAll();
		for (Type type : types) {
			if (type.getId() == heroChoice) {
				playerHero.setName(heroName);
				playerHero.setType(type.getType());
				playerHero.setAttack(type.getAttack());
				playerHero.setDefense(type.getDefense());
				playerHero.setMagic(type.getMagic());
				playerHero.setHealth(type.getHealth());
				break;
			}
		}

		// System.out.println(types);
		int customStat = STATS;

		System.out.printf("Now to customize your %s Hero!\n", playerHero.getType());
		System.out.println("you can customize your Attack, Defense and Magic points..");
		System.out.printf(" and you have %d points available.\n", customStat);
		System.out.printf("How many points do you want to add to your %d attack points?: ", playerHero.getAttack());
		// System.out.println(playerHero);
		contrib = sc.nextInt();
		playerHero.setAttack(playerHero.getAttack() + contrib);
		customStat = customStat - contrib;

		System.out.printf(" and you have %d points available.\n", customStat);
		System.out.printf("How many points do you want to add to your %d defense points?: ", playerHero.getDefense());
		contrib = sc.nextInt();
		playerHero.setDefense(playerHero.getDefense() + contrib);
		customStat = customStat - contrib;

		System.out.printf(" then you have %d points available.\n", customStat);
		System.out.printf(" Adding %d points to your %d magic points.\n ", customStat, playerHero.getMagic());
		playerHero.setMagic(playerHero.getMagic() + contrib);

		System.out.print("Your final hero is: ");
		System.out.println(playerHero);
		System.out.println("now lets battle!");

		int playerHealth = playerHero.getHealth();
		int enemyHealth = playerHero.getHealth();
		int attackChoice = 0;
		
		for (int i = 100; i > 0; i--) {
			System.out.printf("Player Health = %d and Enemy Health = %d \n",playerHealth, enemyHealth);
			if (playerHealth >= 0 && enemyHealth >= 0) {
				System.out.println("Attack!");
				System.out.print("1. for Physical or 2. for Magic Attack!.. choose:");
				attackChoice = sc.nextInt();
				switch(attackChoice) {
				
				case 1:// physical
					System.out.println("Physical attack!--- ");
					enemyHealth = attack(enemyHealth, playerHero.getAttack(), 20);

					break;
					
				case 2:// magic
					enemyHealth = attack(enemyHealth, playerHero.getMagic(), 20);
					break;
					default:
						System.out.println("only choices 1 or 2... try again");
				}
			}else if( playerHealth <= 0 && enemyHealth >= 0) {
				System.out.println("you Lose.");
				return;
			}else if( playerHealth >= 0 && enemyHealth <= 0) {
				System.out.println("you Win!");
				return;
			}
			playerHealth = attack(playerHealth, 10, playerHero.getDefense());
			System.out.println(" Now the enemy attacks the player!!");
		
		}
System.out.println("end");
	}


public static int attack(int health, int attacker, int defender) {
	int damage  = (int) ( (health  )- ( attacker + (defender * Math.random()) * Math.random()) );;
	
	return damage;
}
}
/*
 * "Battle Duel" User Stories 1. As a user, I can create a hero to duel an enemy
 * 2. As a user, I can choose my hero's name, strength, and health 3. As a user,
 * I can take turns with the computer opponent until one of us wins Code
 * Requirements 1. A command line application that takes input from the user 2.
 * An entity wins when the other entity has 0 or less than 0 health 3. Must have
 * some sort of random element: the game should play differently each time, even
 * with the same stats for the hero 4. Must have an abstract class Combatant
 * with an abstract method attack(Combatant c) that child classes will implement
 * 5. Must have an interface to add some special ability that child classes can
 * use in combat Bonus 1. Hero can battle a group of enemies
 * 
 */