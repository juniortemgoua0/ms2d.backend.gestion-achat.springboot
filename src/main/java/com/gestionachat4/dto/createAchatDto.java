package com.gestionachat4.dto;

import java.util.Date;

public class createAchatDto {

    private short codeclient ;

    private short codeproduit ;

    private  int qte ;

    private Date dateachat ;


    public short getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(short codeclient) {
        this.codeclient = codeclient;
    }


    public short getCodeproduit() {
        return codeproduit;
    }

    public void setCodeproduit(short codeproduit) {
        this.codeproduit = codeproduit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDateachat() {
        return dateachat;
    }

    public void setDateachat(Date dateachat) {
        this.dateachat = dateachat;
    }

    @Override
    public String toString() {
        return "createAchatDto{" +
                "codeclient=" + codeclient +
                ", codeproduit=" + codeproduit +
                ", qte=" + qte +
                ", dateachat=" + dateachat +
                '}';
    }
}
