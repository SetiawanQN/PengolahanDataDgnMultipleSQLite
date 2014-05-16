package com.qomatech.datashowromqodri.model;

public class DaftarHargaMobil {
	
	Integer id, daftarhargamobilId, hargamobilId;
	String daftarhargamobil, hargamobil;
	
	public DaftarHargaMobil(){
		
	}
	
	public DaftarHargaMobil(Integer daftarhargamobilId, Integer hargamobilId){
		this.daftarhargamobilId = daftarhargamobilId;
		this.hargamobilId = hargamobilId;
	}
	
	public DaftarHargaMobil(Integer id, String daftarhargamobil, String hargamobil){
		this.id = id;
		this.daftarhargamobil = daftarhargamobil;
		this.hargamobil = hargamobil;
	}
	
	public Integer getId(){
		return id;
	}
	public String getDaftarHargaMobil(){
		return daftarhargamobil;
	}
	public Integer getDaftarHargaMobilId(){
		return daftarhargamobilId;
	}
	public String getHargaMobil(){
		return hargamobil;
	}
	public Integer getHargaMobilId(){
		return hargamobilId;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public void setDaftarHargaMobil(String daftarhargamobil){
		this.daftarhargamobil = daftarhargamobil;
	}
	public void setDaftarHargaMobilId(Integer daftarhargamobilId){
		this.daftarhargamobilId = daftarhargamobilId;
	}
	public void setHargaMobil(String hargamobil){
		this.hargamobil = hargamobil;
	}
	public void setHargaMobilId(Integer hargamobilId){
		this.hargamobilId = hargamobilId;
	}

}
