package com.example.iread.model;

import java.util.Date;

public class DefiAccepted {
        private String uiSender;
        private int Livre;
        private Date dateEnd;
        private int Note;
        public DefiAccepted(){}
        public DefiAccepted(String uiSender, int livre, Date dateEnd, int note){
            this.uiSender=uiSender;
            this.Livre= livre;
            this.dateEnd=dateEnd;
            this.Note=note;
        }

        public String getUiSender() {
            return uiSender;
        }

        public void setUiSender(String uiSender) {
            this.uiSender = uiSender;
        }

        public int getLivre() {
            return Livre;
        }

        public void setLivre(int livre) {
            Livre = livre;
        }

        public Date getDateEnd() {
            return dateEnd;
        }

        public void setDateEnd(Date dateEnd) {
            this.dateEnd = dateEnd;
        }

        public int getNote() {
            return Note;
        }

        public void setNote(int note) {
            Note = note;
        }

}
