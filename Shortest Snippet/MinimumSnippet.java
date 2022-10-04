import java.util.ArrayList;
import java.util.List;

public class MinimumSnippet {
			
		private int position = 0;
		private int beginAdding = 0;		// 0 = don't begin adding to current array list, 1 = do begin to add
		private ArrayList<String> current;
		private ArrayList<String> shortest;
		private ArrayList<String> term;			//Use this for get position only
		private int isThereAShortest = 0;		// 0 if there is already a shortest snippet, 1 if there is not
		private int hasAll = 1;					//Will determine if the Array list has all the needed terms 
		private int beginPoint = 0;					//Beginning point of shortest snippet
	
	public MinimumSnippet(Iterable<String> document, List<String> terms) {

		term = new ArrayList<String>();
		for(String t : terms) {				//I wrote this after I completed constructor, so it's only used for get Position
			term.add(t);
		}
		
		if(terms.size() == 0) {
			throw new IllegalArgumentException("You did not provide any terms! ('See, in this world there are two kinds of people, my friend. Those with terms, and those who dig. You dig.'");
		}
		
		current = new ArrayList<String>();
		shortest = new ArrayList<String>();
		
		for( String s : document) {
			
			if(beginAdding == 0) {		//Determines that we are not currently adding to the Array List
				
			 if(terms.contains(s)) {		//Once you find a term, start adding to an array list
				 
				 current.add(s);
				 beginAdding = 1;
				 beginPoint = position;
				 position += 1;				//Always end everything by incrementing position
				 
				 for(String t : terms)	{		//double check to see if theres only one needed term
						if(current.contains(t) == false) {	
							hasAll = 0;	
						}
						}
				 
				 
					if(hasAll == 1) {						//Congrats, we have an array list w/ all necessary terms
						
						for(int i = 0; i < current.size(); i++ ) {		// You wont have a nonzero snippet smaller than 1		
								shortest.add(current.get(i));
						}
						
							isThereAShortest = 1;
					}
						hasAll = 1; //Reset for next loop
				 
				 }
			 
			 else {
			position += 1;					//If its not a search term, move on
			 }
	
		} else {							// do this if we are still adding to the current array list
				if(terms.contains(s)) {
					
					if (current.get(0).equals(s)) {		//if the current term is the same as the first term then we no longer
						
						current.remove(0);			//need it in the first term so we make the snippet shorter until we find a term we need
						beginPoint += 1;
						
						while(terms.contains(current.get(0)) == false) {	//No point in having terms we aren't looking for in the front
							current.remove(0);								//cutting the fat
							beginPoint += 1;
						}
						
						for(int i = 1; i < current.size(); i ++) {		//Comparing all terms after the first to the first
							
							if(current.get(i).equals(current.get(0))) {		//After the removal we must make sure that there is no unnecessary double terms
								
								current.remove(0);	//cutting unnecessary duplicate terms
								beginPoint += 1;
								
								while(terms.contains(current.get(0)) == false) {
									
									current.remove(0);					//trim the fat
									beginPoint += 1;
									
								}
								i = 1;								//Reset the counter now that we have a new first term
							}
						}
						
						
					}
						current.add(s);		//After making sure we have no unnecessary duplicates we can finally add the term
						position += 1;		//And also increment the position
					for(String t : terms)	{		//Now we see if our Array list contains all the terms we need
						if(current.contains(t) == false) {	
							hasAll = 0;	//one of the terms not found, you dont have them all
							}
						
						}
					
				if(hasAll == 1) {	//Congrats, we have an array list w/ all necessary terms
					if (isThereAShortest == 0) { 
							
						for(int i = 0; i < current.size(); i++ ) {				
							shortest.add(current.get(i));	//No shortest? Then the new array list is the shortest
					}		
						isThereAShortest = 1; 	//There is now a shortest
					}
					else if(current.size() < shortest.size()) {
						
					
							shortest.clear();
						
						for(int i = 0; i < current.size(); i++ ) {				//Current shorter than the shortest? "Look at me, I am the shortest now"
							shortest.add(current.get(i));
					}	
					}
				}	//From here we continue the same process with current. Who knows the next term may make it even shorter
					hasAll = 1; //Reset for next loop
					}
				else {
					current.add(s);		//Adding non searched for terms
					position += 1;
				}
		}
		}
		
	}
		
	

	public boolean foundAllTerms() {
		
		if(isThereAShortest == 0) {			//If there's no shortest list w/ all terms, then the doc doesn't have all the terms
			return false;					
		}
		else {
			return true;
		}
	}

	public int getStartingPos() {
	
		return beginPoint;

	}

	public int getEndingPos() {
		
		
		return beginPoint + shortest.size() - 1;

	}

	public int getLength() {
	
		return shortest.size();

	}

	public int getPos(int index) {
		
		return beginPoint + current.indexOf(term.get(index));

	}

}
