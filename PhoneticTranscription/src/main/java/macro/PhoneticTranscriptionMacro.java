package macro;

public class PhoneticTranscriptionMacro {
	//声母
	public final static String initial_consonant_pattern= "b,p,m,f,"
														+ "d,t,n,l,"
														+ "g,k,h,"
														+ "j,q,x,"
														+ "zh,ch,sh,r,"
														+ "z,c,s,"
														+ "a,e,o,w,y";
	
	//韵母
	public final static String syllablea_vowel= "i,u,v,         "+
												"a,ia,ua,       "+
												"o,uo,          "+
												"e,ie,ve,       "+
												"ai,uai,        "+
												"ei,uei,        "+
												"ao,iao,        "+
												"ou,iou,        "+
												"ou,iou,        "+
												"an,ian,uan,van,"+
												"en,in,uen,vn,  "+
												"ang,iang,uang, "+
												"eng,ing,ueng,  "+
												"ong,iong       ";
	
	public PhoneticTranscriptionMacro() {
	}

}
