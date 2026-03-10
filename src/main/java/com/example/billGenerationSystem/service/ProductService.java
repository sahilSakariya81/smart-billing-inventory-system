package com.example.billGenerationSystem.service;

import com.example.billGenerationSystem.model.ProductModel;
import com.example.billGenerationSystem.repository.ProductRepo;
import com.opencsv.CSVWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.admin.email}")
    private String adminEmail;
    // Save Products
    public void addAllProduct(List<ProductModel> list) {
        productRepo.saveAll(list);
    }


    public File genrateCSVFile(){
        String folderPath = "reports";
        File folder = new File(folderPath);

        if(!folder.exists()){
            folder.mkdirs();
        }
        String fileName = "products_"+  LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))+".csv";
        File csvFile = new File(folder,fileName);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(csvFile),StandardCharsets.UTF_8);
            CSVWriter csvWriter = new CSVWriter(writer);

            //Header
            csvWriter.writeNext(new String[]{"ID","Name","Price","Stock","GST","Threshold"});

            //add all table data
            List<ProductModel> productModelList = productRepo.findAll();
            for(ProductModel model : productModelList){
                csvWriter.writeNext(new String[]{
                        String.valueOf(model.getId()),
                        model.getName(),
                        String.valueOf(model.getPrice()),
                        String.valueOf(model.getStock()),
                        String.valueOf(model.getGst()),
                        String.valueOf(model.getThreshHold())
                });
            }
            csvWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }

        return csvFile;
    }

    @Scheduled(cron = "0 30 14 * * ?",zone = "Asia/Kolkata")
    public void sendProductCsvToAdmin() throws MessagingException {
        File csvFile = genrateCSVFile();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);

        messageHelper.setTo(adminEmail);
        messageHelper.setSubject("Daily Product Report");
        messageHelper.setText("Please Find the Attached Product Report");

        messageHelper.addAttachment(csvFile.getName(), csvFile);

        mailSender.send(message);

//        csvFile.delete();


    }





//    // ✅ Generate CSV as byte[]
//    public byte[] generateCsv() throws Exception {
//
//        List<ProductModel> products = productRepo.findAll();
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
//
//        CSVWriter csvWriter = new CSVWriter(writer);
//
//        // Header
//        String[] header = {"ID", "Name", "Price", "Stock", "GST", "Threshold"};
//        csvWriter.writeNext(header);
//
//        // Data rows
//        for (ProductModel product : products) {
//            String[] data = {
//                    String.valueOf(product.getId()),
//                    product.getName(),
//                    String.valueOf(product.getPrice()),
//                    String.valueOf(product.getStock()),
//                    String.valueOf(product.getGst()),
//                    String.valueOf(product.getThreshHold())
//            };
//            csvWriter.writeNext(data);
//        }
//
//        csvWriter.close();
//
//        return out.toByteArray();  // ✅ return byte[]
//    }

//    @Scheduled(cron = "0 0 18 * * ?",zone = "Asia/Kolkata")
//    public void sendProductCsvToAdmin() throws Exception {
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo("sahilsakariya32@gmail.com");
//        helper.setSubject("Product Report CSV");
//        helper.setText("Please find attached product report.");
//
//        // Generate CSV
//        byte[] csvBytes = generateCsv();
//
//        // Add attachment
//        helper.addAttachment(
//                "products_" + LocalDate.now() + ".csv",
//                new ByteArrayResource(csvBytes),
//                "text/csv"
//        );
//
//        mailSender.send(message);
//    }
}
