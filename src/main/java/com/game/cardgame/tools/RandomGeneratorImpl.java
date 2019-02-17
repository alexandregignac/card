package com.game.cardgame.tools;

import java.util.Random;

import com.game.cardgame.tools.interfaces.RandomGenerator;

import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorImpl implements RandomGenerator {
    
    public Integer[] getRandomNumbers(Integer listSize){
		Random rgen = new Random();	
		Integer[] array = new Integer[listSize];
 
		for(Integer i=0; i< listSize; i++){
			array[i] = i+1;
		}
 
		for (Integer i=0; i<array.length; i++) {
		    Integer randomPosition = rgen.nextInt(array.length);
		    Integer temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
 }