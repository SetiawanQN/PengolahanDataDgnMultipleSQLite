package com.qomatech.datashowromqodri.model;

public class HargaMobil {
	Integer id;
	String hargamobil;
	
	public HargaMobil(){
		
	}
	
	public HargaMobil(String hargamobil){
		this.hargamobil = hargamobil;
	}
	
	public HargaMobil(Integer id, String hargamobil){
		this.id = id;
		this.hargamobil = hargamobil;
	}
	
	public Integer getId(){
		return id;
	} 
	public String getHargaMobil(){
		return hargamobil;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public void setHargaMobil(String hargamobil){
		this.hargamobil = hargamobil;
	}

}
