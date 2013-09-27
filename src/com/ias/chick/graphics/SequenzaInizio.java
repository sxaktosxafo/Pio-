package com.ias.chick.graphics;

import com.ias.chick.Game;
import com.ias.chick.input.Keyboard;
import com.ias.chick.sound.Sound;

public class SequenzaInizio
{
	
	private Image story[]=new Image[6];   
	
	private static int showTime = 5; //tempo di visualizzazione in secondi!!!
	private boolean execute;
	private long now;
	
	Keyboard input;
	
	public SequenzaInizio(Keyboard k)
	{
	    story[0]=new Image("/images/st1.png",400,225);
	    story[1]=new Image("/images/st2.png",400,225);
	    story[2]=new Image("/images/st3.png",400,225);
	    story[3]=new Image("/images/st4.png",400,225);
	    story[4]=new Image("/images/st5.png",400,225);
		story[5]=new Image("/images/start.png",400,225);
		
	    System.out.println("oggetto storia OK");
		
	    this.input=k;
	    
	    execute=true;
	    
	}
	
	public void mostra(){
		
		new Thread(){

	       public void run(){
	    	   
	    	   for(int i=0; i<story.length; i++)
	    	   { 
	    		   if(execute==true && !input.space)
	    		   {
	    			   
	    			   switch (i)
	    			   {
	    			   case 0: showTime=4; break;
	    			   case 1: showTime=4; break;
	    			   case 2: showTime=6; break;
	    			   case 3: showTime=8; break;
	    			   case 4: showTime=5; break;
	    			   case 5: showTime=6; break;
	    			   }
	    			   
	    			   Game.game.displayImage(story[i], showTime*30);
	    			   Sound.story[i].play();
					   now=System.currentTimeMillis();
					   //System.out.println("1"+execute);
					   
					   while((System.currentTimeMillis()-now)<showTime*990)
					   {
						   if(input.space)
						   {
								execute=false;
							    mostra();
						   }
					   }
	    		   }
	    	   }
	       }
		}.start();
	}
}
	