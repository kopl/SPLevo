package org.splevo.tests.statements;

import java.util.ArrayList;

public class LoopStatements {

	public LoopStatements() {
	}
	
	public void ForUnchanged(){
		
		for (int i = 0; i < 10; i++) {
			System.out.println(""+i);
		}
	}
	
	public void ForChanged(){
		
		for (int i = 5; i < 10; i++) {
			System.out.println(""+i);
		}
	}
	
	public void EnhancedForUnchanged(){
		
		ArrayList<String> items = new ArrayList<String>();
		for (String item : items) {
			System.out.println(item);
		}
	}
	
	public void EnhancedForChanged(){

		ArrayList<String> items = new ArrayList<String>();
		ArrayList<String> items2 = new ArrayList<String>();
		for (String item : items2) {
			System.out.println(item);
		}
	}
	
	public void WhileUnchanged(int a){
		
		while(a < 10){
			System.out.println("endlessLoop");
		}
	}
	
	public void WhileChanged(int a){
		
		while(a < 10 && a > 5){
			System.out.println("endlessLoop");
		}
	}

}
