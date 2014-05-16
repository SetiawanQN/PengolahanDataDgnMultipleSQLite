package com.qomatech.datashowromqodri.model;

public class SpesifikasiMobil {
	Integer id;
	String spesifikasimobil;
	
	public SpesifikasiMobil(){
		
	}
	
	public SpesifikasiMobil(String spesifikasimobil){
		this.spesifikasimobil = spesifikasimobil;
	}
	
	public SpesifikasiMobil(Integer id, String spesifikasimobil){
		this.id = id;
		this.spesifikasimobil = spesifikasimobil;
	}
	
	public Integer getId(){
		return id;
	} 
	public String getSpesifikasiMobil(){
		return spesifikasimobil;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public void setSpesifikasiMobil(String spesifikasimobil){
		this.spesifikasimobil = spesifikasimobil;
	}
	
}
