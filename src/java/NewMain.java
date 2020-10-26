
package Rep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class NewMain {
    public static void main(String[] args) throws IOException {
        
        CreateCOmExcel();
    }
     public  static void CreateCOmExcel () throws FileNotFoundException, IOException{
            
    HSSFWorkbook workbook=new HSSFWorkbook( new FileInputStream("Repartition.xls"));
    HSSFWorkbook workbook1=new HSSFWorkbook( new FileInputStream("Repartition1.xls"));
    HSSFSheet sheet =workbook.getSheetAt(0);
    HSSFSheet sheet1 =workbook1.getSheetAt(0);
    workbook1.removeSheetAt(0);
        boolean x=true;
        int i=0;
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
                    cell.setCellValue("REPARTITION ET UTULISATION DE LA SUBVENTION DE FONCTIONNEMENT POUR L’EXERCICE  2015\n" +
                            "AU TITRE DU F.N.R.S.D.T \n" +
                            "(SITUATION PAR LABORATOIRE ARRETE AU 31/12/2016)");
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
                    cell.setCellValue("Solde au 31/12/2015 (1)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue("0.00");
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 4:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("Montant recouvré en 2016 (2)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue("420000000");
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 5:{
                    HSSFCell cell=row.createCell(1);
                    cell.setCellValue("Montant à ventilet (1)+(2)");
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(5);
                    cell.setCellValue("420000001");
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
                    cell.setCellValue("EXERCICES 2016");
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
            for (int j=0;j<20;j++){
                String s="Remboursemmentkkkkkkkkkkkkkkkkkkkkogkoppppppppppppjjjjjjjjjjjjjjjjjjjjjjj";
                int k=s.length()/27;
                int z=s.length()-(k*27);
                int w=0;
                if (k==0){
                row =sheet.createRow(i);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(j);
                cell.setCellStyle(cellStyleCenter);
                CellRangeAddress r=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue("Remboursemme");
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                cell=row.createCell(5);
                cell.setCellValue("120000");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue("28% ");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue("32389");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellValue("87");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellValue("26%");
                cell.setCellStyle(cellStyleCenter);
                i++;
                
                }else{ if (z==0) w=k;else w=k+1;
                    if (i==40 && w==3) i=i+2; else 
                    if (i==41 || w==2)i=i+1;
                row =sheet.createRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i+w-1,1,1);
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(j);
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,2,4);
                sheet.addMergedRegion(r);
                cell=row.createCell(2);
                cell.setCellValue("Remboursemmentkkkkkkkkkkkkkkkkkkkkogkoppppppppppppjjjjjjjjjjjjjjjjjjjjjjj");
                cell.setCellStyle(cellStyleLeft);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,5,5);
                sheet.addMergedRegion(r);
                cell=row.createCell(5);
                cell.setCellValue("120000");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,6,6);
                sheet.addMergedRegion(r);
                cell=row.createCell(6);
                cell.setCellValue("28% ");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,7,7);
                sheet.addMergedRegion(r);
                cell=row.createCell(7);
                cell.setCellValue("32389");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,8,8);
                sheet.addMergedRegion(r);
                cell=row.createCell(8);
                cell.setCellValue("87");
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                r=new CellRangeAddress(i,i+w-1,9,9);
                sheet.addMergedRegion(r);
                cell=row.createCell(9);
                cell.setCellValue("26%");
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
                cell.setCellValue("420");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue("100% ");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue("105");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(8);
                cell.setCellValue("214");
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(9);
                cell.setCellValue("49.03%");
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
            cell.setCellValue("Deux millions");
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
