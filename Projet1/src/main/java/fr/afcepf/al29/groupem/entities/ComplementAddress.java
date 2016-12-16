package fr.afcepf.al29.groupem.entities;

public enum ComplementAddress {
	bis("bis"),a("a"),b("b");
	
	private String complement;
    private ComplementAddress(String adrComp) {
        this.complement = adrComp;
    }
   
    @Override
    public String toString(){
        return complement;
    } 
}
