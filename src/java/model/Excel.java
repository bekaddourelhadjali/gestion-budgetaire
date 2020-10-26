package model;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class Excel {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception { 
        
        Commande c=new Commande();
        c=c.getCommande(5);
        CreateCOmExcel(c.getFournisseur(),c);
    }
    public  static void CreateCOmExcel (Fournisseur f,Commande c) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException, ParseException{
            String nom="";
            String entrep="";
            if(f.getType().equals("entreprise")){
                entrep=f.getNom();
            }else{
                nom=f.getNom();
            }
    HSSFWorkbook workbook=new HSSFWorkbook( new FileInputStream("COMMANDEMODEL.xls"));
    HSSFWorkbook workbook1=new HSSFWorkbook( new FileInputStream("COMMANDE.xls"));
    HSSFSheet sheet =workbook.getSheetAt(0);
    HSSFSheet sheet1 =workbook1.getSheetAt(0);
    workbook1.removeSheetAt(0);
        boolean x=true;
        int i=0;
        HSSFRow row =sheet.getRow(i);
            CellStyle cellStyleCenter = row.getSheet().getWorkbook().createCellStyle();
            CellStyle cellStyleLeft = row.getSheet().getWorkbook().createCellStyle();
            CellStyle cellStyleFont = row.getSheet().getWorkbook().createCellStyle();
            cellStyleCenter.setBorderBottom(BorderStyle.THIN);
            cellStyleCenter.setBorderLeft(BorderStyle.THIN);
            cellStyleCenter.setBorderRight(BorderStyle.THIN);
            cellStyleCenter.setBorderTop(BorderStyle.THIN);
            cellStyleCenter.setWrapText(true);
            cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
            cellStyleLeft.setBorderBottom(BorderStyle.THIN);
            cellStyleLeft.setBorderLeft(BorderStyle.THIN);
            cellStyleLeft.setBorderRight(BorderStyle.THIN);
            cellStyleLeft.setBorderTop(BorderStyle.THIN);
            cellStyleLeft.setWrapText(true);
            
            cellStyleFont.setBorderBottom(BorderStyle.THIN);
            cellStyleFont.setBorderLeft(BorderStyle.THIN);
            cellStyleFont.setBorderRight(BorderStyle.THIN);
            cellStyleFont.setBorderTop(BorderStyle.THIN);
            cellStyleFont.setWrapText(true); 
            HSSFFont  font = row.getSheet().getWorkbook().createFont(); ;

            font.setFontHeightInPoints((short) 8);
            cellStyleFont.setFont(font);
        while (x){
            
            row =sheet.getRow(i);
            switch(i){
                case 0:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("REPUBLIC ALGERIENNE DEMOCRATIQUE  ET POPULAIRE\n" +
                            "MINISTERE DE L'ENSEIGNEMENT SUPERIEUR  ET DE LA RECHERCHE SCIENTIFIQUE\n" +
                            "BON DE COMMANDE\n" +
                            "N "+c.getNumero()+"    DE : "+ConvertDate(c.getDate()) );
                    cell.setCellStyle(cellStyleCenter);
                    break;
                }
                case 8:{
                    
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("Nom et Prénom : "+nom);
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 9:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("Ou raison sociale (mentionner la forme juridique :"+entrep+")");
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 10:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("Agissant pour le compte de : "+f.getAgissant());
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 11:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("Adresse : "+f.getAdresse());
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 12:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("Téléphone et Fax : "+f.getTelephone());
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 13:{
                    HSSFCell cell=row.createCell(2);
                    HSSFCell cell1=row.createCell(6);
                    cell.setCellValue("N R.C : "+f.getNrc());
                    cell1.setCellValue("N.I.F : "+f.getNif());
                    cell.setCellStyle(cellStyleLeft);
                    cell1.setCellStyle(cellStyleLeft);
                    break;
                }
                case 14:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("N d'agrément : "+f.getAgrement());
                    cell.setCellStyle(cellStyleLeft);
                    cell=row.createCell(6);
                    cell.setCellValue("N.I.S : "+f.getNis());
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
                case 15:{
                    HSSFCell cell=row.createCell(2);
                    cell.setCellValue("RIB (ou RIP) : "+f.getRib());
                    cell.setCellStyle(cellStyleLeft);
                    
                    break;
                }
                case 19:{
                    HSSFCell cell=row.createCell(5);
                    cell.setCellValue(c.getObjet());
                    cellStyleLeft.setVerticalAlignment(VerticalAlignment.TOP);
                    cell.setCellStyle(cellStyleLeft);
                    break;
                }
            }
            i++;
            if (i==20)x=false;
        }
        i=22;
        ArrayList<Produit> produits=c.getProduits();
            double  count=0;
            for (int j=0;j<produits.size();j++){
                row =sheet.getRow(i);
                CellRangeAddress r=new CellRangeAddress(i,i,7,8);
                CellRangeAddress r1=new CellRangeAddress(i,i,2,4);
                sheet.addMergedRegion(r1);
                sheet.addMergedRegion(r);
                HSSFCell cell=row.createCell(1);
                cell.setCellValue(j+1);
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(2);
                cell.setCellValue(produits.get(j).getDesignation());
                cell.setCellStyle(cellStyleLeft);
                cell=row.createCell(5);
                cell.setCellValue(produits.get(j).getQuantite());
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(6);
                cell.setCellValue(new DecimalFormat("00.00").format(produits.get(j).getPrix()));
                cell.setCellStyle(cellStyleCenter);
                cell=row.createCell(7);
                cell.setCellValue(new DecimalFormat("##.00").format(produits.get(j).getQuantite()*produits.get(j).getPrix()));
                count=count+produits.get(j).getQuantite()*produits.get(j).getPrix();
                cell.setCellStyle(cellStyleCenter);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r1, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r1, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r1, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r1, sheet);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                i++;
            }
            double d=0;
            for (int j=0;j<3;j++){
                    CellRangeAddress r=new CellRangeAddress(i,i,5,6);
                    CellRangeAddress r2=new CellRangeAddress(i,i,7,8);
                    row =sheet.getRow(i);
                    sheet.addMergedRegion(r);
                    sheet.addMergedRegion(r2);
                HSSFCell cell=row.createCell(5);
                switch (j){
                    case 0 : {
                        cell.setCellValue("Montant en HT");
                        cell=row.createCell(7);
                        cell.setCellValue(new DecimalFormat("##.00").format(count));
                        cell.setCellStyle(cellStyleCenter);
                        break;
                    }
                    case 1 : {
                        cell.setCellValue("Montant de la TVA("+(int)(c.getTva()*100)+"%)");
                        cell=row.createCell(7);
                        cell.setCellValue(new DecimalFormat("##.00").format(count*c.getTva()));
                        String s=new DecimalFormat("##.00").format(count*c.getTva());
                        d=Double.parseDouble(s.replace(",", "."));
                        cell.setCellStyle(cellStyleCenter);
                        break;
                    }
                    case 2: {
                        cell.setCellValue("Montant en TTC");
                        cell=row.createCell(7);
                        cell.setCellValue(new DecimalFormat("##.00").format(count+d));
                        cell.setCellStyle(cellStyleCenter);
                        break;
                    }
                }
                RegionUtil.setBorderBottom(BorderStyle.THIN, r2, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r2, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r2, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r2, sheet);
                RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
                RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
                i++;
                
            }
            row =sheet.getRow(i++);
            CellRangeAddress r=new CellRangeAddress(i,i+2,1,8);    
            sheet.addMergedRegion(r); 
            row =sheet.createRow(i++);
            HSSFCell cell=row.createCell(1);
            if ((double)Math.round(((count+d-(int)(count+d))*100)*100d/100d)==0){
            cell.setCellValue("Arrête le présent de commode à la somme de (en lettres) :\n"+FrenchNumberToWords.convert(count+d)+" DA.");
            }
            else {
                cell.setCellValue("Arrête le présent de commode à la somme de (en lettres) :\n"
                        +FrenchNumberToWords.convert(count+d)+" DA et "+FrenchNumberToWords.convert((double)Math.round(((count+d-(int)(count+d))*100)*100d/100d))+" centimes.");
            }
            
            cell.setCellStyle(cellStyleLeft);
            RegionUtil.setBorderBottom(BorderStyle.THIN, r, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, r, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, r, sheet);
            RegionUtil.setBorderTop(BorderStyle.THIN, r, sheet);
            i=i+2;
            row =sheet.getRow(i);
            r=new CellRangeAddress(i,i+2,1,8);
            sheet.addMergedRegion(r); 
            row =sheet.createRow(i);
            cell=row.createCell(1);
            cell.setCellValue("-Le prestataire s’engage à exécuter la présente commande selon les conditions arrêtées.\n" +
                                "-La source de financement :le budget de fonctionnement du Laboratoire LabRI (ESI-SBA) de l’exercice "+c.getDate().substring(0, 4)+"\n" +
                                "-Le délai de livraison ou d’exécution est estimé l’exercice "+c.getDate().substring(0, 4)+" à compter de la date \n" +
                                "De signature du présent bon de commande. ");
            cell.setCellStyle(cellStyleFont);
            RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
            
            i=sheet.getLastRowNum()+1;
            row =sheet.createRow(i);
            r=new CellRangeAddress(i,i,6,8);    
            sheet.addMergedRegion(r); 
            cell=row.createCell(6);
            cell.setCellValue("LE DIRECTEUR DE LABORATOIRE");
            cell.setCellStyle(cellStyleLeft);
            RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
            
            i=48;
            row =sheet.getRow(i);
            r=new CellRangeAddress(i,i+2,1,8);    
            sheet.addMergedRegion(r); 
            row =sheet.createRow(i);
            cell=row.createCell(1);
            cell.setCellValue("• Conformément aux dispositions notamment de l’article 20 du décret présidentiel n 15-247 du Dhou EL Hidja 1436 correspondant au 16 septembre\n"
                    + "2015 portant règlementation des marchés publics et des délégations de service public \n" +
                    "• L’établissement de la facture par le prestataire doit correspondre à la description de la présente commande et à ce titre, i y lieu de rependre\n"
                    + "des références ou du présent bon de commande sur la dite facture ");
            font.setFontHeightInPoints((short) 6);
            cellStyleFont.setFont(font);
            cell.setCellStyle(cellStyleFont);
            RegionUtil.setBorderBottom(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderLeft(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderRight(BorderStyle.NONE, r, sheet);
            RegionUtil.setBorderTop(BorderStyle.NONE, r, sheet);
            
            workbook.setPrintArea(0, 0, 8, 0, 100);
        workbook1=workbook;        
        workbook1.write(new FileOutputStream("COMMANDE"+c.getNumero()+".xls"));
        workbook1.close();
    }
    public static String ConvertDate(String s) throws ParseException{
        final String OLD_FORMAT = "yyyy-MM-dd"; 
        final String NEW_FORMAT = "dd/MM/yyyy";
            s.replace("-", "/");
            String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(s);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
    return newDateString;
    }
    
}
