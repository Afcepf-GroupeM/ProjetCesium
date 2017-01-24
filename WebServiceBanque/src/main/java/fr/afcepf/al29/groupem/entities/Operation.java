package fr.afcepf.al29.groupem.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

    
    private Integer id;

    private String label;

    private BigDecimal amount;

    private Date dateOp;
    
    public Operation() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

    


}