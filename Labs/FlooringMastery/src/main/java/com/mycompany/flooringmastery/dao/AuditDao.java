/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Audit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AuditDao {

    private List<Audit> auditsList;
    private File auditLogFile;

    public AuditDao(ConfigDao configDao) {
        this.auditLogFile = configDao.get().getAuditLogFile();
        //this.auditLogFile = new File("auditLog.txt");
        auditsList = decode();
    }

    public AuditDao(java.io.File auditLogFile) {
        this.auditLogFile = auditLogFile;
        //this.auditLogFile = new File("auditLog.txt");
        auditsList = decode();
    }

    public Audit create(Audit audit) {
        auditsList.add(audit);
        encode(audit);
        return audit;
    }

    public void update(Audit audit) {
        encode(audit, auditLogFile);
    }

    private void encode(Audit audit) {
        encode(audit, auditLogFile);
    }
    
    private void encode(Audit audit, File auditLogFile) {

        final String TOKEN = "\t";
        final String DATAHEADER = "Date\t Order Id\t Action Performed";

        boolean newLog = false;

        if (!auditLogFile.exists()) {
            newLog = true;
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(auditLogFile), true)) {

            if (newLog) {
                out.println(DATAHEADER);
            }

            out.println(convertAuditToString(audit, TOKEN));

            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(AuditDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String convertAuditToString(Audit audit, String TOKEN) {
        String auditString = audit.getDate() + TOKEN + audit.getOrderid() + TOKEN + audit.getActionPerformed();
        return auditString;
    }

    private Audit buildAuditFromString(String auditString) {

        //String[] auditStringArray = auditString.split(TOKEN);
        Audit audit = new Audit();

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

            cal.setTime(sdf.parse(auditString));
            Date date = new Date();
            date.setTime(cal.getTimeInMillis());
            audit.setDate(date);

        } catch (ParseException ex) {
            Logger.getLogger(AuditDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            audit.setOrderid(Integer.parseInt(auditString));
        } catch (NumberFormatException ex) {
            Logger.getLogger(AuditDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        audit.setActionPerformed(auditString);

        //String auditString = audit.getDate() + TOKEN + audit.getOrderid() + TOKEN + audit.getActionPerformed();
        return audit;
    }

    private List<Audit> decode() {
        return decode(auditLogFile);
    }

    private List<Audit> decode(File auditLogFile) {

        final String TOKEN = "\t";
        final String DATAHEADER = "Date\t Order Id\t Action Performed";

        List<Audit> tempAuditList = new ArrayList();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(auditLogFile)))) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                if (currentLine.equalsIgnoreCase(DATAHEADER)) {

                } else if (!currentLine.trim().isEmpty()) {

                    String[] stringParts = currentLine.split(TOKEN);

                    for (String auditString : stringParts) {
                        Audit audit = buildAuditFromString(auditString);
                        tempAuditList.add(audit);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuditDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tempAuditList;
    }
}
