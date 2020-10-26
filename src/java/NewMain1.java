


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Article;
import model.Budget;
import model.Repartition;
import model.Section;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class NewMain1 {
    
    public static boolean isEmp(ArrayList<Article> liste, String year){
        boolean empty = true;
        for(Article a : liste){
            if(a.getRepartition(year) != null){
                empty = false;
                break;
            }
        }
        return empty;
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        CreateCOmExcel("2018");
        
    }
     public  static void CreateCOmExcel (String annee) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
            
    HSSFWorkbook workbook=new HSSFWorkbook( new FileInputStream("Repartition.xls"));
    HSSFWorkbook workbook1=new HSSFWorkbook( new FileInputStream("Repartition1.xls"));
    HSSFSheet sheet =workbook.getSheetAt(0);
    HSSFSheet sheet1 =workbook1.getSheetAt(0);
    workbook1.removeSheetAt(0);
        boolean x=true;
        int i=0;
        ArrayList<Repartition> rep=Repartition.getRepartitionByYear(2018);
        HSSFRow row =sheet.getRow(i);
            CellStyle cellStyleCenter = row.getSheet().getWorkbook().createCellStyle();
            CellStyle cellStyleLeft = row.getSheet().getWorkbook().createCellStyle();
            CellStyle cellStyleFont = row.getSheet().getWorkbook().createCellStyle();
            HSSFFont  font = row.getSheet().getWorkbook().createFont();
            cellStyleCenter.setBorderBottom(BorderStyle.THIN);
            cellStyleCenter.setBorderLeft(BorderStyle.THIN);
            cellStyleCenter.setBorderRight(BorderStyle.THIN);
            cellStyleCenter.setBorderTop(BorderStyle.THIN);
            cellStyleCenter.setWrapText(true);
            cellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
            cellStyleLeft.setBorderBottom(BorderStyle.THIN);
            cellStyleLeft.setBorderLeft(BorderStyle.THIN);
            cellStyleLeft.setBorderRight(BorderStyle.THIN);
            cellStyleLeft.setBorderTop(BorderStyle.THIN);
            cellStyleLeft.setWrapText(true);
            cellStyleLeft.setVerticalAlignment(VerticalAlignment.TOP);
            
            cellStyleFont.setBorderBottom(BorderStyle.NONE);
            cellStyleFont.setBorderLeft(BorderStyle.NONE);
            cellStyleFont.setBorderRight(BorderStyle.NONE);
            cellStyleFont.setBorderTop(BorderStyle.NONE);
            cellStyleFont.setWrapText(true); 

            font.setFontHeightInPoints((short) 8);
            cellStyleFont.setFont(font);
        while (x){
            
            row =sheet.getRow(i);
            switch(i){
                case 0:{
                CellRangeAddress r=new CellRangeAddress(i,i,1,9);
                sheet.addMergedRegion(r);
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("REPARTITION ET UTULISATION DE LA SUBVENTION DE FONCTIONNEMENT POUR L’EXERCICE  "+rep.get(0).getAnnee()+"\n" +
                            "AU TITRE DU F.N.R.S.D.T \n" +
                            "(SITUATION PAR LABORATOIRE ARRETE AU 31/12/"+annee+")");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
                    break;
                }
                case 1:{
                    CellRangeAddress r=new CellRangeAddress(i,i,1,9);
                    sheet.addMergedRegion(r);
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("MINISTERE DE TUTELLE : Ministère de l’Enseignement supérieur et de la Recherche scientifique \n" +
                            "ETABLISSEMENT : ECOLE SUPERIEURE EN INFORMATIQUE DE SIDI BEL ABBES\n" +
                            "Laboratoire : Laboratoire de recherche en Informatique de Sidi Bel-abbes-LabRI-SBA\n" +
                            "Directeur de laboratoire : Pr. MALKI Mimoun\n" +
                            "Date de création de Laboratoire : 02 Décembre 2015");
                    cell.setCellStyle(cellStyleLeft);
                    RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
                    break;
                }
                case 3:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("Solde au 31/12/"+(Integer.parseInt(annee)-1)+" (1)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)-1).getMontant_t());
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 4:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("Montant recouvré en "+annee+" (2)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d()-Budget.getBudgetById(Integer.parseInt(annee)-1).getMontant_t());
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 5:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("Montant à ventilet (1)+(2)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d());
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 7:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("N\nChapitre\nN\nArticles");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(2);
                    cell.setCellValue("INTITULES DES POSTES DE DEPENSE");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(5);
                    cell.setCellValue("EXERCICES "+annee);
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 8:{
                    HSSFCell cell=row.createCell(5);
                    cell.setCellValue("REPARTITION BUDGETAIRE");
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 9:{
                    HSSFCell cell=row.createCell(5);
                    cell.setCellValue("Monatnt à programmer (A)");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(6);
                    cell.setCellValue("%=(A)/(\nTG}*100");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(7);
                    cell.setCellValue("Montant Consommé (B)");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(8);
                    cell.setCellValue("Solde");
                    cell.setCellStyle(cellStyleCenter);
                    cell=row.createCell(9);
                    cell.setCellValue("%=\n(B)/(A)\n*100");
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }

            }
            i++;
            if (i==16)x=false;
        }
        i=12;
        ArrayList<Section> s=Section.GetSection();
        for (Section s1:s){
            ArrayList<Article> articles = s1.getArticles();
            double mp=0,mc=0;
            if(!isEmp(articles,annee)){
                String ss=s1.getDesignation();
                int k=ss.length()/27;
                int z=ss.length()-(k*27);
                int w=0;
                if (k==0){
                row =sheet.createRow(i);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(s1.getNumero());
                cell.setCellStyle(cellStyleCenter);
                CellRangeAddress r=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue(ss);
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                cell=row.createCell(5);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellStyle(cellStyleCenter);
                i++;
                }else{ if (z==0) w=k;else w=k+1;
                    if (i==40 && w==3) i=i+2; else 
                    if (i==41 || w==2)i=i+1;
                row =sheet.createRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i+w-1,1,1);
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(s1.getNumero());
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue(s1.getDesignation());
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,5,5);
                sheet.addMergedRegion(r);
                cell=row.createCell(5);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,6,6);
                sheet.addMergedRegion(r);
                cell=row.createCell(6);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,7,7);
                sheet.addMergedRegion(r);
                cell=row.createCell(7);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,8,8);
                sheet.addMergedRegion(r);
                cell=row.createCell(8);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,9,9);
                sheet.addMergedRegion(r);
                cell=row.createCell(9);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                i=i+w;
                }
                
                for ( Article l:articles){
                    Repartition repp = l.getRepartition(annee);
                    if(repp != null){
                        ss=l.getDesignation();
                k=ss.length()/27;
                z=ss.length()-(k*27);
                w=0;
                if (k==0){
                row =sheet.createRow(i);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(l.getNumero());
                cell.setCellStyle(cellStyleCenter);
                CellRangeAddress r=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue(ss);
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                cell=row.createCell(5);
                cell.setCellValue(repp.getMontant_p());
                mp= mp+repp.getMontant_p();
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue(new DecimalFormat("00.00").format(repp.getMontant_p()/(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d())*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue(repp.getMontant_c());
                mc=mc+ repp.getMontant_c();
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellValue(repp.getSolde());
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellValue(new DecimalFormat("00.00").format(repp.getMontant_c()/repp.getMontant_p()*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                i++;
                
                }else{ if (z==0) w=k;else w=k+1;
                    if (i==40 && w==3) i=i+2; else 
                    if (i==41 || w==2)i=i+1;
                row =sheet.createRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i+w-1,1,1);
                 System.out.println(l.getNumero());
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(l.getNumero());
                   
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue(l.getDesignation());
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,5,5);
                sheet.addMergedRegion(r);
                cell=row.createCell(5);
                cell.setCellValue(repp.getMontant_p());
                mp=mp+repp.getMontant_p();
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,6,6);
                sheet.addMergedRegion(r);
                cell=row.createCell(6);
                cell.setCellValue(new DecimalFormat("00.00").format(repp.getMontant_p()/(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d())*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,7,7);
                sheet.addMergedRegion(r);
                cell=row.createCell(7);
                cell.setCellValue(repp.getMontant_c());
                mc=mc+repp.getMontant_c();
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,8,8);
                sheet.addMergedRegion(r);
                cell=row.createCell(8);
                cell.setCellValue(repp.getSolde());
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,9,9);
                sheet.addMergedRegion(r);
                cell=row.createCell(9);
                cell.setCellValue(new DecimalFormat("00.00").format(repp.getMontant_c()/repp.getMontant_p()*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                i=i+w;
                }
                    }
                }
            }
            String ss=s1.getDesignation();
                int k=ss.length()/27;
                int z=ss.length()-(k*27);
                int w=0;
                if (k==0){
                row =sheet.createRow(i);
                HSSFCell cell=row.createCell(1);
                cell.setCellStyle(cellStyleCenter);
                CellRangeAddress r=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue("TOTAL "+ss);
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                cell=row.createCell(5);
                cell.setCellValue(mp);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue(new DecimalFormat("00.00").format(mp/(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d())*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue(mc);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellValue(mp-mc);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellValue(new DecimalFormat("00.00").format(mc/mp*100));
                cell.setCellStyle(cellStyleCenter);
                i++;
                }else{ if (z==0) w=k;else w=k+1;
                    if (i==40 && w==3) i=i+2; else 
                    if (i==41 || w==2)i=i+1;
                row =sheet.createRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i+w-1,1,1);
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue("TOTAL "+s1.getDesignation());
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,5,5);
                sheet.addMergedRegion(r);
                cell=row.createCell(5);
                cell.setCellValue(mp);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,6,6);
                sheet.addMergedRegion(r);
                cell=row.createCell(6);
                cell.setCellValue(new DecimalFormat("00.00").format(mp/(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d())*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,7,7);
                sheet.addMergedRegion(r);
                cell=row.createCell(7);
                cell.setCellValue(mc);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,8,8);
                sheet.addMergedRegion(r);
                cell=row.createCell(8);
                cell.setCellValue(mp-mc);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,9,9);
                sheet.addMergedRegion(r);
                cell=row.createCell(9);
                cell.setCellValue(new DecimalFormat("00.00").format(mc/mp*100));
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                i=i+w;
                }
            
        }
            
            row =sheet.createRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(2);
                cell.setCellValue("TOTAL GENERALE ");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(5);
                cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d());
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue("100% ");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d()-Budget.getBudgetById(Integer.parseInt(annee)).getMontant_t());
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellValue(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_t());
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellValue(new DecimalFormat("00.00").format(Budget.getBudgetById(Integer.parseInt(annee)).getMontant_t()/Budget.getBudgetById(Integer.parseInt(annee)).getMontant_d()*100)+"%");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
            
            i=sheet.getLastRowNum()+1;
            r=new CellRangeAddress(i,i,1,9);
            sheet.addMergedRegion(r);
            row=sheet.createRow(i);
            cell=row.createCell(1);
            cell.setCellValue("Arrêtée le présent état à la somme de :");
            i=sheet.getLastRowNum()+1;
            r=new CellRangeAddress(i,i,1,9);
            sheet.addMergedRegion(r);
            row=sheet.createRow(i);
            cell=row.createCell(1);
            double f= Budget.getBudgetById(Integer.parseInt(annee)).getMontant_t();
            if ((double)Math.round(((f-(int)(f))*100)*100d/100d)==0){
            cell.setCellValue(FrenchNumberToWords.convert(f)+" DA.");
            }
            else {
                cell.setCellValue(FrenchNumberToWords.convert(f)+" DA et "+FrenchNumberToWords.convert((double)Math.round(((f-(int)(f))*100)*100d/100d))+" centimes.");
            }
            cell.setCellStyle(cellStyleFont);
            i=sheet.getLastRowNum()+2;
            r=new CellRangeAddress(i,i,2,3);
            sheet.addMergedRegion(r);
            row=sheet.createRow(i);
            cell=row.createCell(2);
            cell.setCellValue("Agent Comptable");
            r=new CellRangeAddress(i,i,4,6);
            sheet.addMergedRegion(r);
            cell=row.createCell(4);
            cell.setCellStyle(cellStyleCenter);
            RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
            cell.setCellValue("Directeur de Laboratoire");
            cell=row.createCell(8);
            cell.setCellValue("Directeur");
            
            workbook.setPrintArea(0, 1, 10, 0, 100);
        workbook.write(new FileOutputStream("Repartition1.xls"));
        workbook.close();
    }
}
