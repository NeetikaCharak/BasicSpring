package main.java;

public enum EnumTest1 {
	MARS(1,100),
	VENUS(2, 200),
	EARTH(3, 300);
	
	private int seq;
	
	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	private int distance;
	
	  EnumTest1(int seq, int distance){
		this.seq = seq;
		this.distance = distance;
	}

	  
	  public static void main(String... args){
		  for (EnumTest1 p : EnumTest1.values())
	           System.out.printf("Your Sequence on %s is %s and distance is %s%n",
	                             p, p.getSeq(), p.getDistance());
	  }
	 
	 
	

}
