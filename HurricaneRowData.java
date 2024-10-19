/**
Programmer: Lucio Plancarte
Created: 09 SEP 2024
Description: Creates HurricaneRowData objects from a csv String
HurricaneRowData.java
*/

/**
	Objects that hold data from a csv string. Properties are the columns
	Setters and getters to access.
	Override of toString() to print/display ace value and year
*/
class HurricaneRowData{

	private int year;
	private int aceIndex;
	private int numTropicalStorms;
	private int numHurricaneAll;
	private int numHurricaneMaj;

	public HurricaneRowData(int y, int a, int ts, int hA, int hM){
		setYear(y);
		aceIndex = a;
		numTropicalStorms = ts;
		numHurricaneAll = hA;
		numHurricaneMaj = hM;
	}//end constructor

	//Setters
	public void setYear(int y){
		year = y;
	}
	public void setAceIndex(int a){
		aceIndex = a;
	}
	public void setNumTropicalStorms(int ts){
		numTropicalStorms = ts;
	}
	public void setHurricaneAll(int hA){
		numHurricaneAll = hA;
	}
	public void setHurricaneMaj(int hM){
		numHurricaneMaj = hM;
	}

	//Getters
	public int getYear(){
		return year;
	}
	public int getAceIndex(){
		return aceIndex;
	}
	public int getNumTropicalStorms(){
		return numTropicalStorms;
	}
	public int getNumHurricaneAll(){
		return numHurricaneAll;
	}
	public int getNumHurricaneMaj(){
		return numHurricaneMaj;
	}
	//toString()
	@Override
	public String toString(){
		return String.format
		("%8d %8d %8d %8d %8d",
		getYear(),
		getAceIndex(),
		getNumTropicalStorms(),
		getNumHurricaneAll(),
		getNumHurricaneMaj()
		);
	}

	@Override
	public boolean equals(Object o){
		if(o == this)
			return true;
		if(!(o instanceof HurricaneRowData))
			return false;

		HurricaneRowData hrd = (HurricaneRowData) o;
		return (
				this.year == hrd.getYear() &&
				this.aceIndex == hrd.getAceIndex() &&
				this.numTropicalStorms == hrd.getNumTropicalStorms() &&
				this.numHurricaneAll == hrd.getNumHurricaneAll() &&
				this.numHurricaneMaj == hrd.getNumHurricaneMaj()
		);
	}

	

}//end class


